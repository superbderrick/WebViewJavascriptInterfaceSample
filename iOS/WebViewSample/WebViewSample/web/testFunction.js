var logAreas;

function helloToClient()
{
  //interface.testFunction('hello Native');
}

function setup() {
  logAreas = document.getElementById('logAreas')
}

function writeLOG() {
  if(logAreas)
    logAreas.innerHTML += "clicked from Native ";
}
