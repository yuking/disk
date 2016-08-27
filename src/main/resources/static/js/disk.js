function add() {
    $('#newDir').show();
    $('#dirName').focus().select();
}

var parent = $('#currentParent').val();

function ok() {
    var name = $('#dirName').val();
    location.href = '../add/' + parent + '/' + name;
}

function no() {
    $('#newDir').hide();
}

function enter(e) {
    location.href = e.currentTarget.id;
}

var $checkAll = $('#checkAll');
var $subBox = $('.fileNode');

$checkAll.click(function() {
    $subBox.prop("checked",this.checked);
});
$subBox.click(function(){
    $checkAll.prop("checked", $('.fileNode:checked').length == $subBox.length);
});

function deleteDir() {
    var id_array = [];
    $('.fileNode:checked').each(function () {
        id_array.push($(this).val());//向数组中添加元素
    });
    var idstr = id_array.join(',');//将数组元素连接起来以构建一个字符串
    location.href = '../delete/'+ parent + '/' + idstr;
}

$('#fileSelect').click(function () {
    $('#fileElem').click();
});

$('#fileElem').change(function () {
    this.form.submit();

});

