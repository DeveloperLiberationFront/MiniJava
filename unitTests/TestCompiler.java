import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FilenameFilter;
import java.io.IOException;
import java.io.Reader;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameter;
import org.junit.runners.Parameterized.Parameters;

import compiler.Failure;

/*
 * A class that runs the compiler, but doesn't run
 * the resulting code. Checks for errors.
 */
@RunWith(Parameterized.class)
public class TestCompiler {

	@Parameter(value = 0)
	public File inputFile;
	@Parameter(value = 1)
	public File outputFile;
	
	@Parameters(name="{0}")
    public static Collection<Object[]> params() {
    	
    	String[] jFiles = new File("unitTests//").list(new FilenameFilter() {
			public boolean accept(File dir, String name) {
				return name.endsWith(".j");
			}
		});
    	
    	Collection<Object[]> pairs = new ArrayList<Object[]>();
    	for(String jFile : jFiles){
    		pairs.add(new File[] { new File("unitTests//"+jFile), new File("unitTests//"+jFile.replace(".j", ".out"))});
    	}
 
        return pairs;
    }

	@Before
	public void setUp(){
		assertFalse(outputFile.exists());
	}
	
	@After
	public void tearDown(){
		if(outputFile.exists())
			outputFile.delete();
	}
	
	@Test
	public void basicTest() throws IOException {
		compileAndCheck();
	}
	
	private void compileAndCheck() throws IOException{
		
		assertTrue(inputFile.exists());
		
		StorageHandler handler = new StorageHandler();
		try {
			Reader reader = new FileReader(inputFile);
			Compiler.compile(handler, 
							reader, 
							inputFile.getAbsolutePath(), 
							outputFile.getAbsolutePath());
		} catch (FileNotFoundException e) {
			handler.report(new Failure("Cannot open input file " + inputFile));
		}
		
		if(inputFile.getName().startsWith("bad")){
			//expected failure is first line of adjacent .ref file
			File f = new File(inputFile.getPath().replace(".j", ".mjc.ref"));
			String expectedFailure = Files.readAllLines(f.toPath()).get(0);
			assertEquals(expectedFailure,handler.aFailure());
		}else{
			assertTrue(outputFile.exists());
			assertThat(sLines(outputFile),is(sLines(outputFile)));
		}
		
	}
	
	private List<String> sLines(File f) throws IOException{
		List<String> lines = Files.readAllLines(f.toPath());
		//skip the first line -- it contains a link to the source file
		return (lines.subList(1, lines.size()-1));
	}

}
