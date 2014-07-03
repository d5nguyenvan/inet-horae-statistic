$(function () {

    var $content = $('#page-content');
    var $window = $(window);

    var onResize = function () {
        $content.height($window.height() - 100);
    };

    $window.resize(onResize);

    var dataSource = new DataSource({
        columns: [
            {
                label: 'STT',
                type: 'rownumber',
                align: 'center',
                width: 100
            },
            {
                property: 'agency_name',
                label: iNet.resources.admin.knobstick.agency_name_field,
                sortable: true,
                type: 'label'
            },
            {
                property: 'number_of_sent',
                label: iNet.resources.admin.knobstick.number_of_sent_field,
                sortable: true,
                type: 'label',
                align: 'right',
                width: 150,
                renderer: function (v) {
                    if (v > 0) {
                        return String.format('<a href="javascript:;">{0}</a>', v);
                    }
                    return v;
                }
            },
            {
                property: 'number_of_received_success',
                label: iNet.resources.admin.knobstick.number_of_received_success,
                sortable: true,
                type: 'label',
                align: 'right',
                width: 150,
                renderer: function (v, data) {
                    return v || 0;
                }
            },
            {
                property: 'number_of_received',
                label: iNet.resources.admin.knobstick.number_of_received_field,
                sortable: true,
                type: 'label',
                align: 'right',
                width: 150,
                renderer: function (v, record) {
                    return record.number_of_sent+ record.number_of_received;
                }
            }
        ]
    });

    //~============BASIC SEARCH ====================
    var BasicSearch = function () {
        this.id = 'knobstick-report-basic-search';
        this.url = iNet.getUrl('admin/report/knobstick/execute.json');
    };
    iNet.extend(BasicSearch, iNet.Component, {
        intComponent: function () {
            this.$fromDate = $('#knobstick-report-search-basic-txt-fromdate');
            this.$toDate = $('#knobstick-report-search-basic-txt-todate');
            this.$type = $('#knobstick-report-search-basic-select-type');
/*
            var now = new Date();
            this.$toDate.attr('value', now.format('d/m/Y'));
            now.setDate(now.getDate() - 7);
            this.$fromDate.attr('value', now.format('d/m/Y'));

            var fromDate = this.$fromDate.datepicker({
                format: 'dd/mm/yyyy'
            }).on('changeDate',function (ev) {
                    if (ev.date.valueOf() > toDate.date.valueOf()) {
                        var newDate = new Date(ev.date);
                        newDate.setDate(newDate.getDate() + 1);
                        toDate.setValue(newDate);
                    }
                    fromDate.hide();
                }).data('datepicker');

            var toDate = this.$toDate.datepicker({
                format: 'dd/mm/yyyy'
            }).on('changeDate',function (ev) {
                    toDate.hide();
                }).data('datepicker');

            this.$fromDate.next().on('click', function () {
                $(this).prev().focus();
            });
            this.$toDate.next().on('click', function () {
                $(this).prev().focus();
            });

*/
        },
        getUrl: function () {
            return this.url;
        },
        getId: function () {
            return this.id;
        },
        getData: function () {
            var __data = {
                type: 'basic',
                term: { from: this.$fromDate.val().trim(), to: this.$toDate.val(), type: this.$type.val()}
            };
            return __data;
        }
    });

    var grid = new iNet.ui.grid.Grid({
        id: 'knobstick-report-grid',
        //url: '#',
        firstLoad: false,
        dataSource: dataSource,
        idProperty: 'agency_id',
        params: {
            term: {}
        },
        basicSearch: BasicSearch,
      /*
        convertData: function (data) {
            var __data = data || {};
            var __items = __data.items || [];
            var number_of_received = 0;
            var number_of_sent = 0;
            for (var i = 0; i < __items.length; i++) {
              var __record = __items[i] || {};
              number_of_received += __record.number_of_received;
              number_of_sent += __record.number_of_sent;
            }
            $('#knobstick-number-of-received').text(number_of_received);
            $('#knobstick-number-of-sent').text(number_of_sent);
            return __items;
        },
        */
        pageSize: 100
    });
    grid.loadData([{"agency_id":"529d1ac3e4b0c7926ebf525a","agency_code":"00.84.H29","agency_name":"Ban quản lý Khu đô thị mới Thủ Thiêm","number_of_sent":0,"number_of_received":11,"number_of_received_success":0,"number_of_received_failure":0,"number_of_not_received":11},{"agency_id":"529d1a84e4b0c7926ebf522c","agency_code":"00.81.H29","agency_name":"Ban quản lý các Khu liên hợp xử lý chất thải","number_of_sent":0,"number_of_received":1,"number_of_received_success":0,"number_of_received_failure":0,"number_of_not_received":1},{"agency_id":"529d1a98e4b0c7926ebf523a","agency_code":"00.82.H29","agency_name":"Ban quản lý khu Chế xuất – công nghiệp","number_of_sent":0,"number_of_received":24,"number_of_received_success":24,"number_of_received_failure":0,"number_of_not_received":0},{"agency_id":"52779c36e4b0f478e09b97fe","agency_code":"00.35.H29","agency_name":"Ban quản lý khu Công nghệ cao","number_of_sent":2,"number_of_received":57,"number_of_received_success":42,"number_of_received_failure":1,"number_of_not_received":14},{"agency_id":"529d1aade4b0c7926ebf524b","agency_code":"00.83.H29","agency_name":"Ban quản lý khu Công viên lịch sử - văn hóa dân tộc","number_of_sent":0,"number_of_received":2,"number_of_received_success":0,"number_of_received_failure":0,"number_of_not_received":2},{"agency_id":"52a1279ae4b046e426df451e","agency_code":"00.85.H29","agency_name":"Ban quản lý khu Nam","number_of_sent":4,"number_of_received":31,"number_of_received_success":31,"number_of_received_failure":0,"number_of_not_received":0},{"agency_id":"52a127a9e4b046e426df4555","agency_code":"00.29.G01","agency_name":"Công an Thành phố","number_of_sent":0,"number_of_received":148,"number_of_received_success":0,"number_of_received_failure":0,"number_of_not_received":148},{"agency_id":"529d1ae5e4b0c7926ebf526f","agency_code":"00.29.G12","agency_name":"Cục Thuế Thành phố","number_of_sent":0,"number_of_received":311,"number_of_received_success":0,"number_of_received_failure":0,"number_of_not_received":311},{"agency_id":"529d1ad6e4b0c7926ebf5265","agency_code":"00.29.G05","agency_name":"Cục Thống kê","number_of_sent":0,"number_of_received":54,"number_of_received_success":0,"number_of_received_failure":0,"number_of_not_received":54},{"agency_id":"529d1b04e4b0c7926ebf5282","agency_code":"00.29.J01","agency_name":"Hội Bảo trợ bệnh nhân nghèo","number_of_sent":0,"number_of_received":0,"number_of_received_success":0,"number_of_received_failure":0,"number_of_not_received":0},{"agency_id":"529d1b13e4b0c7926ebf528c","agency_code":"00.29.I04","agency_name":"Hội Liên hiệp Phụ nữ","number_of_sent":1,"number_of_received":9,"number_of_received_success":9,"number_of_received_failure":0,"number_of_not_received":0},{"agency_id":"529d1b22e4b0c7926ebf5293","agency_code":"00.29.I06","agency_name":"Hội Nông dân Thành phố","number_of_sent":6,"number_of_received":12,"number_of_received_success":12,"number_of_received_failure":0,"number_of_not_received":0},{"agency_id":"529d1b42e4b0c7926ebf52a8","agency_code":"00.29.J25","agency_name":"Liên hiệp các tổ chức hữu nghị TPHCM","number_of_sent":0,"number_of_received":3,"number_of_received_success":0,"number_of_received_failure":0,"number_of_not_received":3},{"agency_id":"529d1b52e4b0c7926ebf52b1","agency_code":"00.29.J26","agency_name":"Liên minh hợp tác xã","number_of_sent":0,"number_of_received":5,"number_of_received_success":0,"number_of_received_failure":0,"number_of_not_received":5},{"agency_id":"529d1b33e4b0c7926ebf529f","agency_code":"00.29.I02","agency_name":"Liên đoàn Lao động","number_of_sent":0,"number_of_received":2,"number_of_received_success":0,"number_of_received_failure":0,"number_of_not_received":2},{"agency_id":"529d1b66e4b0c7926ebf52bb","agency_code":"00.02.H29","agency_name":"Sở Công Thương","number_of_sent":898,"number_of_received":1815,"number_of_received_success":1815,"number_of_received_failure":0,"number_of_not_received":0},{"agency_id":"52779c13e4b0f478e09b97f8","agency_code":"00.04.H29","agency_name":"Sở Giao Thông Vận Tải","number_of_sent":1092,"number_of_received":1117,"number_of_received_success":1117,"number_of_received_failure":0,"number_of_not_received":0},{"agency_id":"529d1b75e4b0c7926ebf52c6","agency_code":"00.03.H29","agency_name":"Sở Giáo dục và đào tạo","number_of_sent":0,"number_of_received":52,"number_of_received_success":0,"number_of_received_failure":0,"number_of_not_received":52},{"agency_id":"529d1b88e4b0c7926ebf52d2","agency_code":"00.06.H29","agency_name":"Sở Khoa học Công nghệ","number_of_sent":5,"number_of_received":91,"number_of_received_success":91,"number_of_received_failure":0,"number_of_not_received":0},{"agency_id":"531fd529e4b0e7f1850f28bf","agency_code":"00.05.H29","agency_name":"Sở Kế hoạch và Đầu tư","number_of_sent":1621,"number_of_received":3437,"number_of_received_success":3437,"number_of_received_failure":0,"number_of_not_received":0},{"agency_id":"529d1b97e4b0c7926ebf52da","agency_code":"00.08.H29","agency_name":"Sở Ngoại vụ","number_of_sent":31,"number_of_received":1658,"number_of_received_success":1658,"number_of_received_failure":0,"number_of_not_received":0},{"agency_id":"52779c02e4b0f478e09b97f5","agency_code":"00.09.H29","agency_name":"Sở Nội Vụ","number_of_sent":54,"number_of_received":882,"number_of_received_success":882,"number_of_received_failure":0,"number_of_not_received":0},{"agency_id":"52779be5e4b0f478e09b97ef","agency_code":"00.33.H29","agency_name":"Sở Quy Hoạch Kiến Trúc","number_of_sent":1578,"number_of_received":1261,"number_of_received_success":1261,"number_of_received_failure":0,"number_of_not_received":0},{"agency_id":"52779ba5e4b0f478e09b97e3","agency_code":"00.14.H29","agency_name":"Sở Thông Tin và Truyền Thông","number_of_sent":238,"number_of_received":340,"number_of_received_success":340,"number_of_received_failure":0,"number_of_not_received":0},{"agency_id":"52779bc8e4b0f478e09b97e9","agency_code":"00.13.H29","agency_name":"Sở Tài Nguyên Môi Trường","number_of_sent":2229,"number_of_received":1442,"number_of_received_success":1442,"number_of_received_failure":0,"number_of_not_received":0},{"agency_id":"52779bf4e4b0f478e09b97f2","agency_code":"00.15.H29","agency_name":"Sở Tư Pháp","number_of_sent":29,"number_of_received":1098,"number_of_received_success":1098,"number_of_received_failure":0,"number_of_not_received":0},{"agency_id":"52779bb7e4b0f478e09b97e6","agency_code":"00.17.H29","agency_name":"Sở Xây Dựng","number_of_sent":2495,"number_of_received":1663,"number_of_received_success":1656,"number_of_received_failure":0,"number_of_not_received":7},{"agency_id":"52779bd7e4b0f478e09b97ec","agency_code":"00.18.H29","agency_name":"Sở Y Tế","number_of_sent":0,"number_of_received":299,"number_of_received_success":299,"number_of_received_failure":0,"number_of_not_received":0},{"agency_id":"529d1ba6e4b0c7926ebf52e5","agency_code":"00.80.H29","agency_name":"Thanh niên xung phong","number_of_sent":0,"number_of_received":14,"number_of_received_success":14,"number_of_received_failure":0,"number_of_not_received":0},{"agency_id":"52779c46e4b0f478e09b9801","agency_code":"00.36.H29","agency_name":"Thanh tra Thành phố","number_of_sent":1,"number_of_received":834,"number_of_received_success":834,"number_of_received_failure":0,"number_of_not_received":0},{"agency_id":"53b25c34e4b02cf83bd9ca2b","agency_code":"00.90.H29","agency_name":"Thường trực Ủy ban nhân dân Thành phố","number_of_sent":0,"number_of_received":12,"number_of_received_success":12,"number_of_received_failure":0,"number_of_not_received":0},{"agency_id":"529d1bdce4b0c7926ebf530b","agency_code":"01.14.H29","agency_name":"Trung tâm Công nghệ thông tin và Truyền thông TPHCM","number_of_sent":10,"number_of_received":39,"number_of_received_success":39,"number_of_received_failure":0,"number_of_not_received":0},{"agency_id":"529d1bf9e4b0c7926ebf531e","agency_code":"01.13.H29","agency_name":"Trung tâm Phát triển quỹ đất","number_of_sent":0,"number_of_received":0,"number_of_received_success":0,"number_of_received_failure":0,"number_of_not_received":0},{"agency_id":"529d1c15e4b0c7926ebf5334","agency_code":"01.02.H29","agency_name":"Trung tâm Tư vấn hỗ trợ doanh nghiệp","number_of_sent":0,"number_of_received":21,"number_of_received_success":0,"number_of_received_failure":6,"number_of_not_received":15},{"agency_id":"52777e54e4b0f478e09b9561","agency_code":"00.00.W29","agency_name":"Trung tâm liên thông Tp. Hồ Chí Minh","number_of_sent":0,"number_of_received":0,"number_of_received_success":0,"number_of_received_failure":0,"number_of_not_received":0},{"agency_id":"529d1bb8e4b0c7926ebf52ef","agency_code":"00.75.H29","agency_name":"Văn phòng BCĐ Giảm nghèo","number_of_sent":0,"number_of_received":2,"number_of_received_success":0,"number_of_received_failure":0,"number_of_not_received":2},{"agency_id":"52779c22e4b0f478e09b97fb","agency_code":"00.34.H29","agency_name":"Văn phòng đăng ký quyền sử dụng đất","number_of_sent":0,"number_of_received":332,"number_of_received_success":0,"number_of_received_failure":0,"number_of_not_received":332},{"agency_id":"537d5e6ae4b02adf3a3b222c","agency_code":"00.96.H29","agency_name":"nss1","number_of_sent":2,"number_of_received":2,"number_of_received_success":2,"number_of_received_failure":0,"number_of_not_received":0},{"agency_id":"537d5e86e4b02adf3a3b235d","agency_code":"00.95.H29","agency_name":"nss2","number_of_sent":2,"number_of_received":2,"number_of_received_success":2,"number_of_received_failure":0,"number_of_not_received":0},{"agency_id":"529542dee4b0c7926ebb793c","agency_code":"00.98.H29","agency_name":"test1","number_of_sent":40,"number_of_received":47,"number_of_received_success":47,"number_of_received_failure":0,"number_of_not_received":0},{"agency_id":"529542f4e4b0c7926ebb794b","agency_code":"00.99.H29","agency_name":"test2","number_of_sent":7,"number_of_received":18,"number_of_received_success":16,"number_of_received_failure":1,"number_of_not_received":1},{"agency_id":"5366fc41e4b00030801a86a0","agency_code":"00.97.H29","agency_name":"test3","number_of_sent":0,"number_of_received":0,"number_of_received_success":0,"number_of_received_failure":0,"number_of_not_received":0},{"agency_id":"529d1af5e4b0c7926ebf527b","agency_code":"00.82.A29","agency_name":"Đảng ủy các khu chế xuất và khu công nghiệp","number_of_sent":0,"number_of_received":0,"number_of_received_success":0,"number_of_received_failure":0,"number_of_not_received":0},{"agency_id":"52779b65e4b0f478e09b97d7","agency_code":"00.58.H29","agency_name":"Ủy ban nhân dân Huyện Bình Chánh","number_of_sent":144,"number_of_received":1759,"number_of_received_success":1759,"number_of_received_failure":0,"number_of_not_received":0},{"agency_id":"52779b76e4b0f478e09b97da","agency_code":"00.59.H29","agency_name":"Ủy ban nhân dân Huyện Cần Giờ","number_of_sent":36,"number_of_received":1276,"number_of_received_success":1276,"number_of_received_failure":0,"number_of_not_received":0},{"agency_id":"529d1a71e4b0c7926ebf521f","agency_code":"00.62.H29","agency_name":"Ủy ban nhân dân Huyện Củ Chi","number_of_sent":13,"number_of_received":1399,"number_of_received_success":1399,"number_of_received_failure":0,"number_of_not_received":0},{"agency_id":"52779b86e4b0f478e09b97dd","agency_code":"00.60.H29","agency_name":"Ủy ban nhân dân Huyện Hóc Môn","number_of_sent":26,"number_of_received":1436,"number_of_received_success":1436,"number_of_received_failure":0,"number_of_not_received":0},{"agency_id":"52779b94e4b0f478e09b97e0","agency_code":"00.61.H29","agency_name":"Ủy ban nhân dân Huyện Nhà Bè","number_of_sent":509,"number_of_received":1369,"number_of_received_success":1360,"number_of_received_failure":1,"number_of_not_received":8},{"agency_id":"529d195de4b0c7926ebf513c","agency_code":"00.41.H29","agency_name":"Ủy ban nhân dân Quận 1","number_of_sent":973,"number_of_received":1751,"number_of_received_success":1751,"number_of_received_failure":0,"number_of_not_received":0},{"agency_id":"529d197ee4b0c7926ebf5150","agency_code":"00.50.H29","agency_name":"Ủy ban nhân dân Quận 10","number_of_sent":17,"number_of_received":1289,"number_of_received_success":815,"number_of_received_failure":141,"number_of_not_received":333},{"agency_id":"52779af4e4b0f478e09b97c2","agency_code":"00.51.H29","agency_name":"Ủy ban nhân dân Quận 11","number_of_sent":372,"number_of_received":1270,"number_of_received_success":1270,"number_of_received_failure":0,"number_of_not_received":0},{"agency_id":"52779b03e4b0f478e09b97c5","agency_code":"00.52.H29","agency_name":"Ủy ban nhân dân Quận 12","number_of_sent":53,"number_of_received":1491,"number_of_received_success":1491,"number_of_received_failure":0,"number_of_not_received":0},{"agency_id":"52779a59e4b0f478e09b97a9","agency_code":"00.42.H29","agency_name":"Ủy ban nhân dân Quận 2","number_of_sent":4,"number_of_received":1669,"number_of_received_success":1662,"number_of_received_failure":0,"number_of_not_received":7},{"agency_id":"52779a7fe4b0f478e09b97b0","agency_code":"00.43.H29","agency_name":"Ủy ban nhân dân Quận 3","number_of_sent":8,"number_of_received":1481,"number_of_received_success":1481,"number_of_received_failure":0,"number_of_not_received":0},{"agency_id":"52779a93e4b0f478e09b97b3","agency_code":"00.44.H29","agency_name":"Ủy ban nhân dân Quận 4","number_of_sent":367,"number_of_received":1241,"number_of_received_success":1241,"number_of_received_failure":0,"number_of_not_received":0},{"agency_id":"52779aa6e4b0f478e09b97b6","agency_code":"00.45.H29","agency_name":"Ủy ban nhân dân Quận 5","number_of_sent":35,"number_of_received":1426,"number_of_received_success":1209,"number_of_received_failure":0,"number_of_not_received":217},{"agency_id":"52779abbe4b0f478e09b97b9","agency_code":"00.46.H29","agency_name":"Ủy ban nhân dân Quận 6","number_of_sent":87,"number_of_received":1447,"number_of_received_success":1447,"number_of_received_failure":0,"number_of_not_received":0},{"agency_id":"529d1995e4b0c7926ebf5160","agency_code":"00.47.H29","agency_name":"Ủy ban nhân dân Quận 7","number_of_sent":25,"number_of_received":1348,"number_of_received_success":907,"number_of_received_failure":31,"number_of_not_received":410},{"agency_id":"52779acfe4b0f478e09b97bc","agency_code":"00.48.H29","agency_name":"Ủy ban nhân dân Quận 8","number_of_sent":200,"number_of_received":1423,"number_of_received_success":1423,"number_of_received_failure":0,"number_of_not_received":0},{"agency_id":"52779ae2e4b0f478e09b97bf","agency_code":"00.49.H29","agency_name":"Ủy ban nhân dân Quận 9","number_of_sent":645,"number_of_received":1585,"number_of_received_success":1585,"number_of_received_failure":0,"number_of_not_received":0},{"agency_id":"52779b25e4b0f478e09b97cb","agency_code":"00.53.H29","agency_name":"Ủy ban nhân dân Quận Bình Thạnh","number_of_sent":730,"number_of_received":1619,"number_of_received_success":1619,"number_of_received_failure":0,"number_of_not_received":0},{"agency_id":"52779b14e4b0f478e09b97c8","agency_code":"00.54.H29","agency_name":"Ủy ban nhân dân Quận Bình Tân","number_of_sent":459,"number_of_received":1441,"number_of_received_success":1441,"number_of_received_failure":0,"number_of_not_received":0},{"agency_id":"529d19abe4b0c7926ebf516f","agency_code":"00.63.H29","agency_name":"Ủy ban nhân dân Quận Gò Vấp","number_of_sent":29,"number_of_received":1317,"number_of_received_success":1317,"number_of_received_failure":0,"number_of_not_received":0},{"agency_id":"52779b34e4b0f478e09b97ce","agency_code":"00.55.H29","agency_name":"Ủy ban nhân dân Quận Phú Nhuận","number_of_sent":242,"number_of_received":1201,"number_of_received_success":1195,"number_of_received_failure":1,"number_of_not_received":5},{"agency_id":"52779b44e4b0f478e09b97d1","agency_code":"00.56.H29","agency_name":"Ủy ban nhân dân Quận Thủ Đức","number_of_sent":96,"number_of_received":1653,"number_of_received_success":1653,"number_of_received_failure":0,"number_of_not_received":0},{"agency_id":"52779b54e4b0f478e09b97d4","agency_code":"00.57.H29","agency_name":"Ủy ban nhân dân Quận Tân Bình","number_of_sent":500,"number_of_received":1497,"number_of_received_success":1496,"number_of_received_failure":0,"number_of_not_received":1},{"agency_id":"529d19c0e4b0c7926ebf5180","agency_code":"00.64.H29","agency_name":"Ủy ban nhân dân Quận Tân Phú","number_of_sent":5,"number_of_received":1264,"number_of_received_success":300,"number_of_received_failure":0,"number_of_not_received":964},{"agency_id":"52779c56e4b0f478e09b9804","agency_code":"00.00.H29","agency_name":"Ủy ban nhân dân Thành Phố","number_of_sent":6902,"number_of_received":5150,"number_of_received_success":5150,"number_of_received_failure":0,"number_of_not_received":0}]);
    onResize();
  grid.on('loaded', function () {
    var store = grid.getStore();
    var number_of_received = 0;
    var number_of_sent = 0;
    var __records = store.values() || [];
    for (var i = 0; i < __records.length; i++) {
      var __record = __records[i] || {};
      number_of_received += __record.number_of_received;
      number_of_sent += __record.number_of_sent;
    }
    $('#knobstick-number-of-received').text(number_of_received);
    $('#knobstick-number-of-sent').text(number_of_sent);
  });

});
