package com.github.igoralmeidaca.bdo.macro;

import org.sikuli.script.*;

import java.util.concurrent.TimeUnit;

public class ProcessTimber {

    private static final Pattern PROCESS_BUTTON = new Pattern(ProcessTimber.class.getResource("process_button.png"));
    private static final Pattern MASS_PROCESS_BUTTON = new Pattern(ProcessTimber.class.getResource("mass_process_button.png"));
    // MOSS TREE
    private static final Pattern MOSS_TIMBER_ICON = new Pattern(ProcessTimber.class.getResource("moss_timber_icon.png"));
    private static final Pattern MOSS_PLANK_ICON = new Pattern(ProcessTimber.class.getResource("moss_plank_icon.png"));
    private static final Pattern MOSS_PLYWOOD_ICON = new Pattern(ProcessTimber.class.getResource("moss_plywood_icon.png"));
    private static final Pattern MOSS_TIMBER_PROCESSING_ICON = new Pattern(ProcessTimber.class.getResource("moss_timber_processing.png"));
    // PINE TREE
    private static final Pattern PINE_TIMBER_ICON = new Pattern(ProcessTimber.class.getResource("pine_timber_icon.png"));
    private static final Pattern PINE_PLANK_ICON = new Pattern(ProcessTimber.class.getResource("pine_plank_icon.png"));
    private static final Pattern PINE_PLYWOOD_ICON = new Pattern(ProcessTimber.class.getResource("pine_plywood_icon.png"));
    private static final Pattern PINE_TIMBER_PROCESSING_ICON = new Pattern(ProcessTimber.class.getResource("pine_timber_processing.png"));
    // MAPPLE TREE
    private static final Pattern MAPLE_TIMBER_ICON = new Pattern(ProcessTimber.class.getResource("maple_timber_icon.png"));
    private static final Pattern MAPLE_PLANK_ICON = new Pattern(ProcessTimber.class.getResource("maple_plank_icon.png"));
    private static final Pattern MAPLE_PLYWOOD_ICON = new Pattern(ProcessTimber.class.getResource("maple_plywood_icon.png"));
    private static final Pattern MAPLE_TIMBER_PROCESSING_ICON = new Pattern(ProcessTimber.class.getResource("maple_timber_processing.png"));
    // ACACIA TREE
    private static final Pattern ACACIA_TIMBER_ICON = new Pattern(ProcessTimber.class.getResource("acacia_timber_icon.png"));
    private static final Pattern ACACIA_PLANK_ICON = new Pattern(ProcessTimber.class.getResource("acacia_plank_icon.png"));
    private static final Pattern ACACIA_PLYWOOD_ICON = new Pattern(ProcessTimber.class.getResource("acacia_plywood_icon.png"));
    private static final Pattern ACACIA_TIMBER_PROCESSING_ICON = new Pattern(ProcessTimber.class.getResource("acacia_timber_processing.png"));

    public static void main(String[] args) throws InterruptedException, FindFailed {
        doRun(ACACIA_TIMBER_ICON, ACACIA_PLANK_ICON, ACACIA_PLYWOOD_ICON, ACACIA_TIMBER_PROCESSING_ICON);
    }

    private static void doRun(Pattern timber, Pattern plank, Pattern plywood, Pattern timberProcessing) throws InterruptedException, FindFailed {
        Screen screen = new Screen(0);
        boolean firstExecution = true;

        while (true) {
            System.out.println("Pressing R");
            screen.keyDown("R");
            screen.keyUp("R");

            TimeUnit.MILLISECONDS.sleep(getRandomInt(1500, 4000));

            System.out.println("Pressing 2");
            screen.keyDown("2");
            screen.keyUp("2");

            if (!firstExecution) {
                TimeUnit.MILLISECONDS.sleep(getRandomInt(1500, 4000));

                Match mossPlywoodIconMatch = screen.wait(plywood);
                Location mossPlywoodIconOffset = mossPlywoodIconMatch.getTarget();
                System.out.println("Moving mouse to find plywood in bag");
                screen.mouseMove(getRandomMoveIcon(mossPlywoodIconOffset));
                mossPlywoodIconMatch.rightClick();
                TimeUnit.MILLISECONDS.sleep(getRandomInt(400, 1000));
                System.out.println("Pressing enter");
                screen.keyDown(Key.ENTER);
                screen.keyUp(Key.ENTER);

                TimeUnit.MILLISECONDS.sleep(getRandomInt(800, 2000));

                Match mossPlankIconMatch = screen.wait(plank);
                Location mossPlankIconTargetOffset = mossPlankIconMatch.getTarget();
                System.out.println("Moving mouse to find plank in bag");
                screen.mouseMove(getRandomMoveIcon(mossPlankIconTargetOffset));
                mossPlankIconMatch.rightClick();
                TimeUnit.MILLISECONDS.sleep(getRandomInt(800, 1000));
                System.out.println("Pressing enter");
                screen.keyDown(Key.ENTER);
                screen.keyUp(Key.ENTER);
            }
            firstExecution = false;

            TimeUnit.MILLISECONDS.sleep(getRandomInt(1000, 3000));

            Match processButtonMatch = screen.wait(PROCESS_BUTTON);
            Location processButtonTargetOffset = processButtonMatch.getTarget();
            System.out.println("Moving mouse to find Process button");
            screen.mouseMove(getRandomMoveProcessButton(processButtonTargetOffset));
            processButtonMatch.click();

            // random move within the stash
            System.out.println("Moving mouse at random inside stash");
            screen.mouseMove(getRandomMoveInsideStorageFrame(processButtonTargetOffset));

            TimeUnit.MILLISECONDS.sleep(getRandomInt(800, 2000));
            System.out.println("Scrolling down inside stash");
            screen.wheel(Button.WHEEL_DOWN, getRandomInt(7, 12));

            Match mossTimberIconMatch = screen.wait(timber);
            TimeUnit.MILLISECONDS.sleep(getRandomInt(600, 3000));
            Location mossTimberIconTargetOffset = mossTimberIconMatch.getTarget();
            System.out.println("Moving mouse to find timber inside stash");
            screen.mouseMove(getRandomMoveIcon(mossTimberIconTargetOffset));
            mossTimberIconMatch.rightClick();

            Match massProcessButtonMatch = screen.wait(MASS_PROCESS_BUTTON);
            TimeUnit.MILLISECONDS.sleep(getRandomInt(1000, 2000));
            Location massProcessButtonTargetOffset = massProcessButtonMatch.getTarget();
            System.out.println("Moving mouse to find Mass Process button");
            screen.mouseMove(getRandomMoveMassProcessButton(massProcessButtonTargetOffset));
            massProcessButtonMatch.click();

            TimeUnit.MILLISECONDS.sleep(10000);

            while (screen.exists(timberProcessing) != null) {
                System.out.println("Waiting timber icon disappear to repeat the processing cycle");
                TimeUnit.MILLISECONDS.sleep(getRandomInt(30000, 60000));
            }

//            screen.wait(HEAVY_INVENTORY_BUTTON.similar((float) 0.50), 600000);
//            TimeUnit.MILLISECONDS.sleep(getRandomInt(590000, 620000));
        }
    }

    public static int getRandomInt(int min, int max) {
        return min + (int) (Math.random() * ((max - min) + 1));
    }

    public static Location getRandomMoveProcessButton(Location currentOffset) {
        return currentOffset.move(getRandomInt(currentOffset.x - 60, currentOffset.x + 60), getRandomInt(currentOffset.y - 21, currentOffset.y + 21));
    }

    public static Location getRandomMoveIcon(Location currentOffset) {
        return currentOffset.move(getRandomInt(currentOffset.x - 13, currentOffset.x + 15), getRandomInt(currentOffset.y - 12, currentOffset.y + 19));
    }

    public static Location getRandomMoveMassProcessButton(Location currentOffset) {
        return currentOffset.move(getRandomInt(currentOffset.x - 80, currentOffset.x + 80), getRandomInt(currentOffset.y - 15, currentOffset.y + 15));
    }

    public static Location getRandomMoveInsideStorageFrame(Location currentOffset) {
//        screen.mouseMove(getRandomInt(200, 350), getRandomInt(-200, -600));
        return currentOffset.move(getRandomInt(currentOffset.x + 200, currentOffset.x + 350), getRandomInt(currentOffset.y - 200, currentOffset.y - 600));
    }

}
