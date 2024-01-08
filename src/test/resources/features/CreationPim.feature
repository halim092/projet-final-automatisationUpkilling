#language: en

Feature: Creation PIM

  @aLancerCreationPim
  Scenario Outline:  Validez la création PIM
    Given je vais sur la page d'accueil "https://opensource-demo.orangehrmlive.com/web/index.php/auth/login"
    And j'entre un username "<username>"
    And j'entre un mot de passe "<mot de passe>"
    And je clique sur le bouton login
    And je clique sur le bouton Pim
    And je clique sur le button Add
    And j'entre le first Name "<first Name>"
    And j'entre le Middle Name "<middle Name>"
    And j'entre le Last Name "<last Name>"
    And j'entre le employee Id "<employee Id>"
    When je clique sur le bouton save
    Then l'employee est cree
    And le titre de la page est "<titre>"
    Examples:
      | username    | mot de passe |first Name|middle Name|last Name|employee Id| titre|
      |Admin        |admin123      |ufur      |fzeeu      | duauyr  |85728      |Personal Details|
