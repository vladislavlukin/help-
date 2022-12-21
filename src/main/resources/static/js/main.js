$(function(){

const appendTask = function(data){
    var taskCode = '<a href="#" class="task-link" data-id="' + data.id + '">' + data.name + '</a><br>';
    $('#task-list').append('<div>' + taskCode + '</div>');
};
$.get('/tasks/', function(response)
{
    for(i in response){
        appendTask(response[i]);
    }
});
$('#show-add-book-form').click(function(){
    $('#task-form').css('display', 'flex');
});
$('#task-form').click(function(event){
    if(event.target === this) {
    $(this).css('display', 'none');
    }
});
$(document).on('click', '.task-link', function(){
    var link = $(this);
    var taskId = link.data('id');
      $.ajax({
            method: "GET",
            url: '/tasks/' + taskId,
            success: function(response)
            {
                var code = '<span>Срок выполнение задачи:' + response.date +  '</span>';
                link.parent().append(code);
                var code1 = '<span>Описание задачи:' + response.text +  '</span>';
                link.parent().append(code1);
            }
            });
            return false;
});
$('#save-task').click(function()
{
    var data = $('#task-form form').serialize();
    $.ajax({
        method:"POST",
        url:'/tasks/',
        data:data,
        success: function(response)
        {
            $('#task-form').css('display', 'none');
            var task = {};
            task.id = response;
            var dataArray = $('#task-form form').serializeArray();
            for(i in dataArray){
            task[dataArray[i]['name']] = dataArray[i]['value'];
            }
            appendTask(task);
        }
        });
        return false;
    });
});
