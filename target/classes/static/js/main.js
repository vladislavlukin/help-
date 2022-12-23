const dialog = document.getElementById('dialog');
const buttonOpenDialog = document.getElementById('show-add-task-form');
const buttonCloseDialog = document.getElementById('close');
const form = document.getElementById('form');
const taskList = document.getElementById('task-list');

buttonOpenDialog.addEventListener('click', () => dialog.showModal());
buttonCloseDialog.addEventListener('click', (evt) => {
    evt.preventDefault();
    dialog.close();
});

form.addEventListener('submit', async evt => {
    evt.preventDefault();

    dialog.close();

    await fetch(form.action, {
        method: 'POST',
        body: new FormData(form),
     });


    const response = await fetch('/tasks/', { method: 'GET' });
    const data = await response.json();

    console.log(data);

});
    taskList.addEventListener('click', () => {
             var data = $('#task-list');
             var link = '<a href="#" class="task-link" data-id="' + data.id + '">' + data.title + '</a><br>';
             var taskId = link.data('id');
               $.ajax({
                     method:"GET",
                     url:'/tasks/' + taskId,
                     success: function(response)
                     {
                         var code1 = '<span> Описание задачи:' + response.description +  '</span>';
                         link.parent().append(code1);
                     }
                     });
                     return false;
         });







