Feature: amazon Search

  Scenario: TC01 user amazonda kelime aratir
    Given user amazon anasayfasinda
    Then user Nutella icin arama yapar
    And sonuclarin Nutella icerdigini test eder
    And sayfayi kapatir
