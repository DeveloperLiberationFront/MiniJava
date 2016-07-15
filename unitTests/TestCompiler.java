import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.nio.file.Files;
import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import compiler.Failure;
import compiler.Handler;
import compiler.SimpleHandler;

public class TestCompiler {

	private File outputFile;
	
	@Before
	public void setUp(){
		outputFile = new File("junit_output.s");
		assertFalse(outputFile.exists());
	}
	
	@After
	public void tearDown(){
		if(outputFile.exists())
			outputFile.delete();
	}
	
	@Test
	public void test() throws IOException {
		
		File oracle = new File("unitTests//ts.s");
		compileAndCheck(new File("unitTests//ts.j"),oracle);
	}
	
	private void compileAndCheck(File inputFile, File oracle) throws IOException{
		
		assertTrue(inputFile.exists());
		
		Handler handler = new SimpleHandler();
		try {
			Reader reader = new FileReader(inputFile);
			Compiler.compile(handler, 
							reader, 
							inputFile.getAbsolutePath(), 
							outputFile.getAbsolutePath());
		} catch (FileNotFoundException e) {
			handler.report(new Failure("Cannot open input file " + inputFile));
		}
		
		assertEquals(0,handler.getNumFailures());
		
		assertTrue(outputFile.exists());
		assertEquals(sLines(oracle),sLines(outputFile));
	}
	
	private List<String> sLines(File f) throws IOException{
		List<String> lines = Files.readAllLines(f.toPath());
		//skip the first line -- it contains a link to the source file
		return (lines.subList(1, lines.size()-1));
	}

}
