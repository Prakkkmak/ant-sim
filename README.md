Bienvenue dans notre simulation de fourmis !
Le projet est composé d'un package main et d'un package test comprenant des tests ( non complets ).

Pour lancer l'application, lancer le main de App.java. La simulation peut y etre changée. 
De la même manière aller dans Species Factory permet de changer le comportement des fourmis.

- Principe:

Le monde est composé de cases elles mêmes composées d'entitées. Lors de la mise à jour du monde chacune des cases est mise
à jour avec chacune des entités qui la compose. Les classes World, Tile, Ant, AntHill sont les classes principales du projet.

Une fourmis suit des phéromone de nouriture en priorité et dépose cette même phéromone lorsqu'elle en transporte.
Une fourmis à tendance à suite une phéromone de maison pour ne pas trop se dispercer. 
Les proies se déplacent en ligne droite.
La fourmilliere est une reine qui créé des oeufs régulierement.
De la nouriture apparait régulierement.
100ms représente 1 minute par défaut.
La classe World sert de façade. L'IHM comunique activement avec cette derniere.

- Complications:

Le projet a été fait assez rapidement. Le manque de conception commence un peu à se ressentir. Les tests tard coutent cher
à créer.

- A faire:

Refaire le projet proprement. 
Ajouter le systeme de déchet à déplacé. 
Ajouter le déplacement aléatoire des proies.
Ajouter une fourmiliere et tester le comportement.
Ajouter les soldats.
Ajouter la peremption des déchets.
Créer le créateur d'éspece dans l'ihm.
Utilisation de Python ?

- Credits :

Le code est libre de modification et de réutilisation.
Voici le code source: https://github.com/Prakkkmak/ant-sim
