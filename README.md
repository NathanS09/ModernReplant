# ModernReplant

ModernReplant
Version : 1.0
Objectifs :
  Création d’un plugin d’auto replant pour les agricultures.

Conception :
  Nom du plugin : ModernReplant
  Version : Minecraft 1.16.4
  Sauvegarde : BDD ou YML
  Source : A fournir
  Dépendances : WorldGuard
  Rédaction : guigui731
  
 Fonctionnement :
  Lorsqu’un joueur casse une agriculture au spawn, celle-ci se replante automatiquement.
  Ce système ne doit fonctionner que dans la zone worldguard définie en config, sur le reste
  de la map, sur les îles, c’est le système vanilla qui reprend le relais.
  Si la pousse n’a pas fini de pousser à son stade maximal, on ne peut pas la casser (vérifier ça
  avec les datas de pousse → ex : blé = data 7)
  
En config :
  ➔ Pouvoir mettre la zone worldguard dans laquelle s’applique cela
  ➔ Pouvoir mettre les agricultures que l’on veut utiliser en config
  - Blé, verrue, carotte, patate (par défaut toutes les mettre)
