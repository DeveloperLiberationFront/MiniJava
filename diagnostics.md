

## dimensions

kinds of diagnostics:
- broken accessibility contract
- name clash
- broken type contract
- unbound program element
    - usually element names, but sometimes things like super()
- unimplemented inherited property
- missing required statement
    - i.e. no return statement in method that returns a value, no super() call in constructor
- signature contract diagnostic
    - i.e. too many/too few parameters to method call
- type mismatch
- unterminated syntax contract

## diagnostics

- AccessibilityContractDiagnostic.java
    - contract
        - accessibility
        - needs implicit/explicit split?
- ExplicitSignatureContractDiagnostic.java
    - contract
        - type? length? arity?
        - explicit
- ExplicitTypeContractDiagnostic.java
    - contract
        - explicit
        - type
- ImplicitTypeContractDiagnostic.java
    - contract
        - implicit
        - type
- ImplicitUnboundNameDiagnostic.java
    - unbound
    - contract
        - name
        - implicit
- MissingInheritedPropertyDiagnostic.java
    - contract
        - explicit
    - unbound name
- MissingReqiredStatementDiagnostic.java
    - contract
        - statement
        - implicit
- NameClashDiagnostic.java
    - clash
        - explicit
        - name
- StatementContractDiagnostic.java
    - contract
        - implicit
    - missing statement :/
- SyntaxRequiresTypeDiagnostic.java
    - type
    - contract
        - implicit
- TypeMismatchDiagnostic.java
    - type
    - contract
        - explicit
- UnboundNameDiagnostic.java
    - unbound name
- UndeclaredSuperclassDiagnostic.java
    - unbound name?
        - more like a dangling reference of some sort
- UnterminatedSyntaxContractDiagnostic.java
    - contract
        - implicit
        - syntax

## interfaces

- AccessibilityDiagnosticInterface.java
- ClashDiagnosticInterface.java
