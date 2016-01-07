function expand(id) {
    if (id.style.display == "none") {
        id.style.display = "";
    } else {
        id.style.display = "none";
    }
    window.event.cancelBubble = true;
}

/**
 * Enterキーを押した場合に押したいボタンを設定する.
 * @param targetButtonId 押したいボタンのid
 * @mara formName フォームの名前 値を渡さない場合は、デフォルトのフォームを設定
 */
function setOnEnterClickButton(targetButtonId,formName){
  var form = (formName == null) ? document.forms[0] : document.forms[formName];
  var targetButton = document.getElementById(targetButtonId);
  document.onkeypress=function(e){
    e = e ? e : event;
    var keyCode= e.charCode ? e.charCode : ((e.which) ? e.which : e.keyCode);
    var elem = e.target ? e.target : e.srcElement;
    if(Number(keyCode) == 13) {//13=EnterKey
      if(!isIgnoreEnterKeySubmitElement(elem)){
        targetButton.click();
      }
      return isInputElement(elem) ? false : true;
    }
  }
}
function isIgnoreEnterKeySubmitElement(elem){
  var tag = elem.tagName;
  if(tag.toLowerCase() == "textarea"){
    return true;
  }
  switch(elem.type){
    case "button":
    case "submit":
    case "reset":
    case "image":
    case "file":
      return true;
  }
  return false;
}
function isInputElement(elem){
  switch(elem.type){
    case "text":
    case "radio":
    case "checkbox":
    case "password":
      return true;
  }
  return false;
}

function checkAll(checkbox, target) {
    var checked = checkbox.checked;
    var elem = document.getElementsByName(target);
    for(i = 0; i < elem.length; i++){
        elem[i].checked = checked;
    }
}

