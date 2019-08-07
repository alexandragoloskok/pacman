package com.pacman

class Pacman {

    public Integer WALL = 0
    public Integer BISCUIT = 1
    public Integer EMPTY = 2
    public Integer BLOCK = 3
    public Integer PILL = 4
    public Integer PACMAN = 5

    public ArrayList MAP = [
            [0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0],
            [0, 1, 1, 1, 1, 1, 1, 1, 1, 0, 1, 1, 1, 1, 1, 1, 1, 1, 0],
            [0, 4, 0, 0, 1, 0, 0, 0, 1, 0, 1, 0, 0, 0, 1, 0, 0, 4, 0],
            [0, 1, 0, 0, 1, 0, 0, 0, 1, 0, 1, 0, 0, 0, 1, 0, 0, 1, 0],
            [0, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 0],
            [0, 1, 0, 0, 1, 0, 1, 0, 0, 0, 0, 0, 1, 0, 1, 0, 0, 1, 0],
            [0, 1, 1, 1, 1, 0, 1, 1, 1, 0, 1, 1, 1, 0, 1, 1, 1, 1, 0],
            [0, 0, 0, 0, 1, 0, 0, 0, 1, 0, 1, 0, 0, 0, 1, 0, 0, 0, 0],
            [2, 2, 2, 0, 1, 0, 1, 1, 1, 1, 1, 1, 1, 0, 1, 0, 2, 2, 2],
            [0, 0, 0, 0, 1, 0, 1, 0, 0, 3, 0, 0, 1, 0, 1, 0, 0, 0, 0],
            [2, 2, 2, 2, 1, 1, 1, 0, 3, 3, 3, 0, 1, 1, 1, 2, 2, 2, 2],
            [0, 0, 0, 0, 1, 0, 1, 0, 0, 0, 0, 0, 1, 0, 1, 0, 0, 0, 0],
            [2, 2, 2, 0, 1, 0, 1, 1, 1, 1, 1, 1, 1, 0, 1, 0, 2, 2, 2],
            [0, 0, 0, 0, 1, 0, 1, 0, 0, 0, 0, 0, 1, 0, 1, 0, 0, 0, 0],
            [0, 1, 1, 1, 1, 1, 1, 1, 1, 0, 1, 1, 1, 1, 1, 1, 1, 1, 0],
            [0, 1, 0, 0, 1, 0, 0, 0, 1, 0, 1, 0, 0, 0, 1, 0, 0, 1, 0],
            [0, 4, 1, 0, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 0, 1, 4, 0],
            [0, 0, 1, 0, 1, 0, 1, 0, 0, 0, 0, 0, 1, 0, 1, 0, 1, 0, 0],
            [0, 1, 1, 1, 1, 0, 1, 1, 1, 0, 1, 1, 1, 0, 1, 1, 1, 1, 0],
            [0, 1, 0, 0, 0, 0, 0, 0, 1, 0, 1, 0, 0, 0, 0, 0, 0, 1, 0],
            [0, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 0],
            [0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0]
    ]


    void transposeMatrix(ArrayList matrix){
        for (int i = 0; i < matrix.size(); i++) {
            for (int j = i+1; j < 19; j++) {
                int temp = matrix[i][j];
                matrix[i][j] = matrix[j][i];
                matrix[j][i] = temp;
            }
        }
    }


    String  due = "LEFT",
            direction = "LEFT"
    Integer eaten    = null,
            lives    = null,
            score    = null,
            posX=9, posY=12


    public void setPosition(HashMap<String, Integer> position){
        posX=position.x
        posY=position.y
        MAP[posX][posY]=5
    }

    public HashMap<String, Integer> getPosition(){
        return new HashMap<String, Integer>("x":posX, "y":posY)
    }

    public void deletePosition(HashMap<String, Integer> position){
        MAP[position.x][position.y]=1
    }



    public void addScore(Integer nScore) {
        score += nScore;
        if (score >= 10000 && score - nScore < 10000) {
            lives += 1;
        }
    };

    public Integer theScore() {
        return score;
    };

    public void loseLife() {
        lives -= 1;
    };

    public Integer getLives() {
        return lives;
    };

    public void initUser() {
        score = 0;
        lives = 3;
        newLevel();
    }

    public void newLevel() {
        resetPosition();
        eaten = 0;
    };

    public void resetPosition() {
        setPosition(new HashMap<String, Integer>("x":12, "y":9))
        direction = "LEFT"
        due = "LEFT"
    };

    public void reset() {
        initUser();
        resetPosition();
    };

    public HashMap<String, Integer> getNewCoord(dir, current) {
        return new HashMap (
                "x": current.x + (dir == "LEFT" ? -1 : dir == "RIGHT" ? 1 : 0),
                "y": current.y + (dir == "DOWN" ? 1 : dir == "UP"    ? -1 : 0)
        )
    };





HashMap<String, Integer> oldPosition = new HashMap<String, Integer>()


    public void start(){

        transposeMatrix(MAP)

        oldPosition = getPosition()
        setPosition(getNewCoord(due, getPosition()))
        deletePosition(oldPosition)

        if(getPosition().x==0){
            due="RIGHT"
        }
        if(getPosition().x==18){
            due="LEFT"
        }
    }


    public ArrayList getMap(){
        start()
        transposeMatrix(MAP)
        MAP
    }
}



//function keyDown(e) {
//    if (typeof keyMap[e.keyCode] !== "undefined") {
//        due = keyMap[e.keyCode];
//        return false;
//    }
//    return true;
//}




//function keyPress(e) {
//    if (state !== WAITING && state !== PAUSE) {
//        e.preventDefault();
//        e.stopPropagation();
//    }
//};
