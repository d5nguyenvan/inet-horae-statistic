iNet.ns("iNet.resources.grid","iNet.resources.admin.knobstick");
if (iNet.resources.grid) {
    iNet.apply(iNet.resources.grid, {
        beforePageText: 'Trang',
        afterPageText: 'của {0}',
        firstText: 'Trang đầu',
        prevText: 'Trang trước',
        nextText: 'Trang sau',
        lastText: 'Trang cuối',
        displayMsg: 'Hiển thị <b>{0}</b> - <b>{1}</b> trong tổng số <b>{2}</b>',
        emptyMsg: 'Không có dữ liệu để hiển thị',
        refreshText: 'Nạp lại',
        searchText: 'Tìm kiếm',
        searchPlaceholder: 'Từ khóa',
        downloadText: 'Tải về'
    });
}

if (iNet.resources.ajaxLoading) {
    iNet.apply(iNet.resources.ajaxLoading, {
        loading: 'Đang tải dữ liệu ...',
        saving: 'Đang lưu dữ liệu ...',
        deleting: 'Đang xóa dữ liệu...'
    });
}

if (iNet.resources.admin.knobstick) {
  iNet.apply(iNet.resources.admin.knobstick, {
    notation_field: 'Số ký hiệu',
    title_field: 'Tiêu đề',
    type_field: 'Loại',
    type_edoc: 'Văn bản điện tử',
    agency_empty: '<div class="red">Tài khoản này chưa được liên kết với bất kỳ tổ chức nào</div>',
    sender_field: 'Nơi gửi',
    receiver_field: 'Nơi nhận',
    status_field: 'Trạng thái',
    status_initial: 'Khởi tạo',
    status_processing: 'Đang xử lý',
    status_fail: 'Lỗi',
    status_done: 'Hoàn thành',
    create_date_field: 'Ngày gửi',
    completed_date_field: 'Ngày hoàn thành',
    at_time: 'lúc',
    agency_code_field: 'Mã đơn vị',
    agency_name_field: 'Tên đơn vị',
    number_of_sent_field: 'Gửi',
    number_of_received_field: 'Tổng số',
    number_of_received_success: 'Đã nhận',
    number_of_received_failure: 'Thất bại',
    number_of_not_received: 'Chưa nhận',
    confirm_delete_content: 'Bạn có chắc chắn là đồng ý muốn xóa những mẫu tin đã chọn ?',
    confirm_clear_title: 'Xóa tất cả ?',
    confirm_clear_content: 'Bạn có chắc chắn là đồng ý muốn xóa tất cả những mẫu tin được tìm thấy ?',
    delete_title: 'Xoá thông tin liên thông',
    delete_error: 'Có lỗi xảy ra trong quá trình xóa thông tin liên thông.',
    clear_title: 'Xóa tất cả thông tin liên thông tìm được',
    clear_error: 'Có lỗi xảy ra trong quá trình xóa tất cả thông tin liên thông tìm được.',
    send_dlg_title: 'Liên thông của đơn vị',
    receive_dlg_title: 'Được liên thông của đơn vị',
    receive: 'NHẬN LIÊN THÔNG'
  });
}