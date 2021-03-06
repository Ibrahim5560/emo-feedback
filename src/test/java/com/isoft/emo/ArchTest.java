package com.isoft.emo;

import static com.tngtech.archunit.lang.syntax.ArchRuleDefinition.noClasses;

import com.tngtech.archunit.core.domain.JavaClasses;
import com.tngtech.archunit.core.importer.ClassFileImporter;
import com.tngtech.archunit.core.importer.ImportOption;
import org.junit.jupiter.api.Test;

class ArchTest {

    @Test
    void servicesAndRepositoriesShouldNotDependOnWebLayer() {
        JavaClasses importedClasses = new ClassFileImporter()
            .withImportOption(ImportOption.Predefined.DO_NOT_INCLUDE_TESTS)
            .importPackages("com.isoft.emo");

        noClasses()
            .that()
            .resideInAnyPackage("com.isoft.emo.service..")
            .or()
            .resideInAnyPackage("com.isoft.emo.repository..")
            .should()
            .dependOnClassesThat()
            .resideInAnyPackage("..com.isoft.emo.web..")
            .because("Services and repositories should not depend on web layer")
            .check(importedClasses);
    }
}
