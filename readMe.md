# CC2 - Développer et utiliser des services web en Java

- Coefficient 15
- TP sur machine en salle TP et à la maison
- Évaluation en groupe de 4 étudiants
- Compétence 6 – « collaborer »

## Le contexte

L’objectif de ce projet est de développer une application web pour une entreprise livrant des repas à des personnes inscrites. L’entreprise propose différents plats (p.ex. salade niçoise, aïoli, gratin dauphinois, etc.) et les clients peuvent combiner ces plats pour constituer des menus pour une personne. Les abonnés peuvent ensuite commander ces menus en ligne et ils leur sont livrés à domicile à une date précise. Le paiement se fera au moment de la livraison.
## L’application

### Description

L’application web devra permettre de visualiser les plats disponibles, leur description et leur prix. Elle devra permettre de composer des menus en y ajoutant/supprimant des plats. Le nom de la personne ayant créé le menu est enregistré, tout comme la date de création (ou de mise à jours). Les menus pourront ensuite être commandés par les abonnés (tous). Un abonné peut commander plusieurs menus dans les quantités souhaitées. Lorsqu’une personne validera une commande, l’application enregistrera les menus associés, enregistrera la date de commande, calculera leur prix au moment de la commande, et enregistrera l’adresse et la date de livraison.

Pour des questions de temps, on supposera que les fonctionnalités associées à l’enregistrement et la mise à jours des plats proposés par l’entreprise ne seront pas intégrées dans l’interface graphique de l’application, tout comme celles liées à l’inscription des abonnés. Par contre, elles seront implémentées dans l’application et prêtes à être intégrer dans l’interface graphique.

### Contraintes non fonctionnelles

L’application suivra une architecture orientée services (RESTful). Elle sera constituée des 4 composants logiciels suivants:
- le composant “IHM” gérera la partie interface graphique avec l’utilisateur.
- le composant “Plats et Utilisateurs” gérera l’accès aux données des plats et des utilisateurs.
- le composant “Menus” gérera les opérations sur les menus
- le composant “Commandes” gérera la partie commande.

Le composant “IHM” sera codé en HTML/CSS/PHP. Les autres composants seront codés avec Jakarata EE sous forme d’API REST implémentant les opérations CRUD. Chaque composant sera codé indépendamment, par un étudiant différent, et utilisera une base de données dédiée.

Par ailleurs, le composant “Menus” devra consommer l’API mise à disposition par le composant “Plats et Utilisateurs” pour son fonctionnement (en évitant de dupliquer trop d’informations dans sa base de données). Le composant “Commandes” consommera quant à lui l’API du composant “Menus”.

Le schéma suivant résume cette architecture:

![schema](https://cdn.discordapp.com/attachments/1185997163570933790/1221724692046413864/image.png?ex=66139ec9&is=660129c9&hm=537958fa2d7e33049f20d63d6f84e8b44abff2327508cd760ee6320dcd26971a& "schéma")

### Les livrables

Chaque composant sera à la charge d’un étudiant du groupe. Pour chacun, il y aura donc deux types de livrables sont attendus: un rapport et une archive avec le code.

Le rapport devra décrire le composant logiciel développé. Il contiendra :

- le cas d’utilisation associé;
- le diagramme de classes;
- une description des fonctionnalités implémentées (préciser si totalement fonctionnelle ou partiellement). Pour les API, il faudra documenter les entrées/sorties attendues. Pour l’IHM, il faudra intégrer des captures d’écran pour illustrer les différents scénarios possibles.

L’archive déposée devra contenir quant à elle :

- le code source commenté,
- la javadoc,
- les tests implémentés

Chaque étudiant devra déposer sur Ametice ces deux fichiers en rapport avec le composant dont il a eu la charge.