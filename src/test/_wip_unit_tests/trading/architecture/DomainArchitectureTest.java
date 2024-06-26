package com.example.service.trading.architecture;

import com.tngtech.archunit.junit.AnalyzeClasses;
import com.tngtech.archunit.junit.ArchTest;
import com.tngtech.archunit.lang.ArchRule;

import static com.tngtech.archunit.lang.syntax.ArchRuleDefinition.classes;

@AnalyzeClasses(packages = "com.example.service.trading")
class DomainArchitectureTest {

    @ArchTest
    static final ArchRule domainModel_shouldOnlyHave_DependenciesWithBasicLibraries_andItsOwnProject =
            ArchRuleDefinition.classes().that()
                    .resideInAnyPackage("..domain..")
                    .should()
                        .onlyHaveDependentClassesThat()
                        .resideInAnyPackage("java..", "javax..", "lombok..",
                                "com.example.service.trading..");
}
