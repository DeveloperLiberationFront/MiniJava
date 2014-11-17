## dimensions

- contract
    - explicit
    - implicit
- accessibility
- type
- name binding
    - unbound name

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
    - unbound name
    - contract
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
