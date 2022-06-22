$(function () {
    $('.btn-delete').click(function () {
        var url = $(this).data('url');
        Swal.fire({
            title: '您确定吗?',
            text: "此操作不可撤销！",
            icon: 'error',
            showCancelButton: true,
            confirmButtonColor: '#3085d6',
            cancelButtonColor: '#d33',
            confirmButtonText: '确定',
            cancelButtonText: '取消'
        }).then((result) => {
            if(result.value) { // 点了确定做什么，由开发者决定
                location.href = url;
            }
        });
    });
});