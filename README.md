# Developing Maintainable Software Coursework

## Table of Contents

- [GitHub](#github)
- [Compilation Instructions](#compilation-instructions)
- [Implemented and Working Properly](#implemented-and-working-properly)
- [Implemented but Not Working Properly](#implemented-but-not-working-properly)
- [Features Not Implemented](#features-not-implemented)
- [New Java Classes](#new-java-classes)
- [Modified Java Classes](#modified-java-classes)
- [Unexpected Problems](#unexpected-problems)

## GitHub

You can find my repository on GitHub here:

[GitHub Repository Link](https://github.com/hybong/bhy_developing24)

## Compilation Instructions

1. **Clone the Repository**:
   To get started, clone the repository to your local machine:
   
   ```bash
   git clone https://github.com/hybong/bhy_developing24.git
2. **Open it**:
   Open the cloned repository in your preferred IDE (eg. **IntelliJ IDEA** or **Eclipse**).
3. Install [JavaFX SDK](https://gluonhq.com/products/javafx/) and save it in your workspace.
4. In your IDE where you manage the **IDE and project settings**, go to **Project Settings** and set the **SDK** to 19 or above.
   Only [Oracle OpenJDK 19.0.2](https://www.oracle.com/java/technologies/javase/jdk19-archive-downloads.html) works for me and I don't know why.
5. Choose language level to be **SDK default**
6. In the libraries section, choose **+ > Java** then browse the JavaFX SDK folder where you saved it.
7. Open **JavaFX folder > lib**.
8. For the dependencies and other settings, use the **pom.xml** and **module-info.java** provided in the project.
9. After setting up, you can now run the project in the **src > main > java > com > example > demo > controller > Main.java**.

## Implemented and Working Properly

### General features
- **Main menu**: Implemented a main menu when the game starts or when the player wants to return to the main menu.
- **Main menu background**: Plays a video in loop in the main menu background.
- **Pause Button**: A button at the top right of the page and when clicked, the game is paused.
- **Play Button**: A button at the top right of the page and only appears when the game is paused, the game resumes when clicked.
- **Pause Menu**: A menu that pops up when the game is paused and disappears when the game is resumed.
- **Win level menu**: A menu that pops up after winning the first three levels and asks if the player wants to proceed or go back to main menu.
- **Lose level menu**: A menu the pops up after losing the game and asks if the player wants to retry or go back to main menu.
- **Win game menu**: A menu that pops up after the player finished the game at Level 4, asking if they want to Exit Game or go back to Main menu.
- **Background Music**: Added background music to all levels and main menu.
- **Sound Effects**: Added sound effects to certain actions including winning the game.
- **Mute/Unmute music**: Adds the control to toggle background music in main menu and pause menu.

### Actors
- **User movement**: Added the ability to move vertically and move faster.
- **Boss healthbar**: Added healthbar to each boss so player can see their progress.
- **User shooting**: Implemented mouse clicks on the screen to shoot.
- **Remove actors and non-actors**: Used when I want to clear the game page but not removing background image and background music, mostly for
  showing win and lose menus.

### Level 1 features
- **KillCount Display**: Shows how many kills have been achieved and how many is needed to proceed to next level.

### Level 2 features
- **Boss's shield**: The boss can only use the shield for a total of 1 time in level 2. The shield appears when the probability of shielding is
  triggered or when the boss is at half health or lower, whichever comes first.

### Level 3 features
- **Second Boss**: This boss will have a higher health. When the boss's health is down to a certain range, the boss will shoot faster and move faster.
- **Boss's shield**: In this level, the probability of shielding will be higher, and the total number of times the boss can use shiedl is increased.
  After the boss is down to the low health range, the boss will continuously use the shield until the number of times it has left is exhausted.

### Level 4 features
- **Two bosses**: This level will have two bosses, Boss and SecondBoss. The player needs to destroy the two bosses at the same time to win the game.
- **Revival of boss**: If one boss is destroyed, the player needs to destroy the other boss, otherwise the dead boss will revive.

## Implemented but Not Working Properly
- **Sound effects**: This is also here because there is no problem with the playing of sound effects, but has a problem of being delayed when called
  to play. This issue does not appear on other computers but only my laptop.
  Also if the player is to shoot very fast, the sound effects will still play but the screen will lag.
  - For this I have thought of a solution, which is to limit the player's shooting speed, but as this was not a major problem in the gameplay
  mechanics, it was leftout due to time constraints and inability.

## Features Not Implemented
- **Limiting player's shooting speed**: As was mentioned earlier, I wanted to implement a method for this, but I can't think of a way to do it
  so I had to skip it and leave it out.
- **Choosing levels**: In the main menu, players should be able to choose which level they want to start from.
- **Difficulty of levels**: Players should be able to customise the game's difficulty according to their liking.
- **Timed game**: Players can lookback at their time records of beating each levels and try to beat their own highscores.
- **Muting and unmuting sound effects**: Players can choose to mute or unmute sound effects like doing the same with backgroud music.
- **Multiplayer mode**: Players can play on the same keyboard while playing together.

These are all not implemented because of the time and effort required to implement them as we are also committing to other course's assignments
and tests, and these are just small additions to the game (except for Multiplayer mode).

## New Java Classes
- **MainMenu** Path: src/main/java/com/example/demo/controller/MainMenu.java
  - to have a home page for the game
- **LoseLevelMenu** Path: src/main/java/com/example/demo/controller/inGameController/LoseLevelMenu.java
  - show options for continuing after the player dies
- **PauseButton** Path: src/main/java/com/example/demo/controller/inGameController/PauseButton.java
  - stops the game
- **PauseMenu** Path: src/main/java/com/example/demo/controller/inGameController/PauseMenu.java
  - shows menu when game is paused
- **PlayButton** Path: src/main/java/com/example/demo/controller/inGameController/PlayButton.java
  - resumes the game
- **WinGameMenu** Path: src/main/java/com/example/demo/controller/inGameController/WinGameMenu.java
  - show options for continuing after the player finished the game
- **WinLevelMenu** Path: src/main/java/com/example/demo/controller/inGameController/WinLevelMenu.java
  - show options for continuing after the player finishe the level
- **LevelViewLevelOne** Path: src/main/java/com/example/demo/Levels/levelView/LevelViewLevelOne.java
  - plays UI including KillCount
- **LevelThree** Path: src/main/java/com/example/demo/Levels/levelThree.java
  - players fights a harder boss than level two
- **LevelViewLevelThree** Path: src/main/java/com/example/demo/Levels/levelView/LevelViewLevelThree.java
  - plays UI including boss shield and boss health
- **LevelFour** Path: src/main/java/com/example/demo/Levels/levelFour.java
  - players fights two bosses to win the game
- **LevelViewLevelFour** Path: src/main/java/com/example/demo/Levels/levelView/LevelViewLevelFour.java
  - plays UI including boss shield and boss health for both bosses
- **BackgroundMusic** Path: src/main/java/com/example/demo/media/BackgroundMusic.java
  - handles background music for levels and main menu
- **SoundEffect** Path: src/main/java/com/example/demo/media/SoundEffect.java
  - handles sound effects of buttons and actors, and also winning game sound
- **SecondBoss** Path: src/main/java/com/example/demo/models/SecondBoss.java
  - a new boss extended from Boss which have different attributes and is harder to beat
- **BossHealth** Path: src/main/java/com/example/demo/views/BossHealth.java
  - a new boss extended from Boss which have different attributes and is harder to beat
- **KillCount** Path: src/main/java/com/example/demo/views/KillCount.java
  - a label to show how much enemy planes are destroyed and how many to kill in level one
- **ReviveTimer** Path: src/main/java/com/example/demo/views/ReviveTimer.java
  - a timer to countdown boss revive time in level four

## Modified Java Classes
### File Refactoring
Moved them into different files for easier management.
- **ShieldImage.java**
  1. This was one of the first debugging. Changed shield.**jpg** to shield.**png**
  2. Changed full image path of shield and use it
  3. changed input parameters to none
- **LevelParent.java**
  1. added `timeline.stop()` so first scene is stopped before proceeding to the second level
  2. added KeyEvent to handle user moving horizontally
  3. changed `updateLevelView()` from private to protected so the levels could access it
  4. added `removeActors()` and `removeAllActors()` to clear the screen
- **LevelOne.java**
  1. added `KillCount` display to notify the player
  2. implemented `WinLevelMenu` after finishing level
  3. implemented manual adjustment of background music volume
- **LevelTwo.java**
  1. changed `winGame()` to `goToNextLevel` for proceeding to level 3
  2. Override method `initializeScene()` to show shield
  3. added boss's health bar
  4. added background music
  5. implemented `WinLevelMenu` after finishing level
  6. implemented manual adjustment of background music volume
  7. implemented `removeNotActors()` which removes non-actors like shield or boss health bar after game is over
- **LevelViewLevelTwo.java**
  1. added `displayShield()` and `updateShieldPosition(Boss boss)` to show and update boss shield position
  2. implemented boss health bar
  3. implemented `removeNotActors()` to remove non-actors to clear screen
- **Main.java**
  1. added main menu
  2. modified screen size to a size suitable
- **UserProjetile.java**
  1. changed `HORIZONTAL_VELOCITY`from 15 to 30
  2. modified size to fit the screen
- **EnemyProjetile.java**
  1. modified size to fit the screen
- **BossProjetile.java**
  1. modified size to fit the screen
- **FighterPlane.java**
  1. implemented `setHealth()` for fighter planes
  2. changed `health` from private to public
  3. implemented fighter plane destroy sound effect
- **UserPlane.java**
  1. Added ability to move left and right and update it's position
  2. changed return value of `fireProjectile()` so projectile follows the user plane
  3. changed upper and ower bounds to ensure the plane doesn't go out of bounds
  4. adjusted user plane projectile offset
  5. implemented **SHIFT** key to move faster
  6. added sound effect for shooting
  7. modified upper and lower bounds to follow screen size
- **EnemyPlane.java**
  1. changed projectile position offset
- **ActiveActorDestructible.java**
  1. changed `setDestroyed(Boolean)` from protected to public for boss revival in level four
- **Boss.java**
  1. changed initial x position and projectile offset
  2. changed size of boss
  3. changed `HEALTH` from private to public so **BossHealth.java** can access boss health.
  4. changed `shieldShouldBeActivated()` from private to protected so **SecondBoss** can use the method
  5. changed `shieldCount` from private to protected so **SecondBoss** can access
  6. changed several fields and `initializeMovePattern()` to protected so **SecondBoss** can use them as well
  7. implemented method `revive()` which resets the boss
  8. edited `fireProjectile()` so shooting sound effect can be added
  9. refactored boss initial **X** and **Y** position to follow the size of the screen
  10. changed `deactivateShield()` from private to puublic for JUnit testing
- **GameOverImage.java**
  1. adjusted position to follow screen size
  2. implemented toFront();
- **WinImage.java**
  1. adjusted position to follow screen size
  2. implemented toFront();
- **HeartDiplay.java**
  1. Implemented `hideHearts()` for other classes to hide the heart display

## Unexpected Problems
1. **Problem**: User plane moved diagonally when I press up or down, or left or right. It does not move in a way that a controller normally will.

   **Solve**: Initailly the user plane is using `VelocityMultiplier` for both horizontal and vertical movement. This was the cause of the user
   plane not working properly. I split the `VelocityMultiplie` into `HorizontalVelocityMultiplier` and `VerticalVelocityMultiplier`. This solved
   the problem where the user plane is moving diagonally.
3. **Problem**: The projectiles and plane collisions happen at a very weird time or position. The enemy projectile hasn't even hit the user plane
   and the user plane took damage.

   **Solve**: Edited the size of the pictures and found out they were super big. After adjustinf the size, there was no more problem
4. **Problem**: Boss revival in level 4 has been a difficult obstacle.
   1. The revived boss image doesn't appear when the boss revives. The image only appear when the other dies.
   2. Revived boss health has lesser health than expected
   3. **SecondBoss** is still in `isInDanger()` state after revival
   
   **Solve**: Slowly tested each method to see where is the problem. After identifying them, think of ways to remove the bugs and make the revival
successful.
