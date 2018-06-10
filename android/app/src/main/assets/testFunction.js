function show(info)
{
    alert(info);
}

function link()
{
    window.launcher.showInfo('From WEB to Native');
}

function check()
{
    if(window && window.launcher)
    {
        alert('pass');
    }
    else
    {
        alert('fail');
    }
}