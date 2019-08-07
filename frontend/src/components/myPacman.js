import axios from "axios";

var ctx, canvas, keyMap = {}, FPS=30,

KEY = {'N':78, 'PAUSE': 19, 'ARROW_LEFT': 37, 'ARROW_UP': 38, 'ARROW_RIGHT': 39, 'ARROW_DOWN': 40};



function createElementCanvas(){
  var wrapper = document.getElementById("pacman"),
      blockSize = wrapper.offsetWidth / 12;
      canvas    = document.createElement("canvas");

  canvas.setAttribute("width", (blockSize * 19) + "px");
  canvas.setAttribute("height", (blockSize * 22) + 30 + "px");

  wrapper.appendChild(canvas);

  ctx  = canvas.getContext('2d');
}

function createAlso(){
  keyMap[KEY.ARROW_LEFT]  = "LEFT";
  keyMap[KEY.ARROW_UP]    = "UP";
  keyMap[KEY.ARROW_RIGHT] = "RIGHT";
  keyMap[KEY.ARROW_DOWN]  = "DOWN";


  document.addEventListener("keydown", keyDown, true);
  // document.addEventListener("keypress", keyPress, true);
}

function getRequest(){
  ctx.clearRect(0, 0, parseInt(canvas.width), parseInt(canvas.height))

  axios.get('game/map')
    .then(_ => {
      console.log(_.data[12])
      var xPos = 50, yPos = 50;

      for (var i = 0; i < _.data.length; i++) {
        for (var j = 0; j < _.data[i].length; j++) {

          if (_.data[i][j] === 0 || _.data[i][j] === 3) {
            drawLetter(ctx, '*', xPos, yPos);
          } else if (_.data[i][j] === 1 || _.data[i][j] === 2 || _.data[i][j] === 4) {
            drawLetter(ctx, ' ', xPos, yPos);
          } else if (_.data[i][j] === 5) {
            drawLetter(ctx, '@', xPos, yPos);
          } else {
            drawLetter(ctx, _.data[i][j], xPos, yPos);
          }
          xPos += 30

        }
        xPos = 50;
        yPos += 30;
      }
    }).catch(_ => {
    console.log(_.response.data.message)
  });
}


function init() {


keyDown(KEY.N)


};
function keyDown(e) {


  if (e.keyCode === KEY.N) {

    getRequest()

  }
}

function drawLetter(ctx, litera, x, y) {
  ctx.beginPath()
  ctx.fillStyle = "white";
  ctx.font = "bold 25px sans-serif";
  ctx.fillText(litera, x, y);
  ctx.closePath()
};

export default{
  startNow(){
    createElementCanvas()
    createAlso()
    window.setInterval(init, 1000 /FPS);
  }
}
