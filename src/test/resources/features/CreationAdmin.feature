#language: en

Feature: Creation ADMIN

  @aLancerCreationAdmin
  Scenario Outline:  Validez la création ADMIN
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
    And je clique sur le menu Admin
    And je clique sur le bouton Add Admin
    And je selectionne le UserRole "<role>"
    And je selectionne le status "<status>"
    And j'entre employee Name "<last Name>"
    And je selectionne le premier nom dans la liste
    And j'entre un username Admin avec mes initiales"<initialUsername>"
    And jentre un mot de passe admin "<passwordAdmin>"
    And je confirme le mote de passe "<passwordAdmin>"
    And je clique sur le bouton Save
    And je clique sur le profil
    And je clique sur le bouton Logout
    And j'entre le username avec les initiales "<initialUsername>"
    And j'entre le passwordAdmin "<passwordAdmin>"
    And je clique sur le bouton login
    Then acceder a Dashboard
    And le nom du profil est egal l'employee Name "<first Name>" et "<last Name>"
    Examples:
      | username    | mot de passe |first Name|middle Name|last Name|employee Id| initialUsername|passwordAdmin|role |status |
      |Admin        |admin123      |ufur      |           | duauyr  |85728      |ud.autom        |Halim1992$   |Admin|Enabled|
