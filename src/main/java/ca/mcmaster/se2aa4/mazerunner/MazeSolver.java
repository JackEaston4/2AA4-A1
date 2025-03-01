package ca.mcmaster.se2aa4.mazerunner;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class MazeSolver{
    
    private final MazeRunner runner;
    private static final Logger logger = LogManager.getLogger();
    private final StringBuilder canonical_path = new StringBuilder();

    public MazeSolver(MazeRunner runner){
        this.runner = runner;
    }

    public String MazeRunnerAlgorithm(){
        while(!runner.isAtFinish()){
            logger.trace("\n-----------------\nLooping through MazeRunnerAlgorithm\n");
            if (!runner.checkForWall(Direction.RIGHT)) {
                logger.trace("no wall to right, turning right");
                runner.turnDirection(Direction.RIGHT);
                recordMove('R');

                runner.movePlayer(); // to prevent infinte loops from when in a '+' intersection
                recordMove('F');
            }
            else if (!runner.checkForWall(Direction.FORWARD)) {
                logger.trace("wall right, no wall forward, moving forward");
                runner.movePlayer();
                recordMove('F');
            }
            else {
                logger.trace("wall right and forward, turning left");
                runner.turnDirection(Direction.LEFT);
                recordMove('L');
            }
        }

        logger.info("Finish reached");
        logger.trace("canonical path: " + canonical_path.toString());
        return canonical_path.toString();
    }


    public void recordMove(char move){
        canonical_path.append(move);
        logger.trace("Recorded move '" + move + "' to " + runner.getPlayerPosition());
        
    }
}