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
                renderer: function (v) {
                    if (v > 0) {
                        return String.format('<a href="javascript:;">{0}</a>', v);
                    }
                    return v;
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
            var me = this;

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
        url: '#',
        dataSource: dataSource,
        idProperty: 'agency_id',
        params: {
            term: {}
        },
        basicSearch: BasicSearch,
        convertData: function (data) {
            var __data = data || {};
            var __items = __data.items || [];
            var number_of_received = 0;
            var number_of_sent = 0;
            if (iNet.isEmpty(__data.total_of_received) || iNet.isEmpty(__data.total_of_sent)) {
                for (var i = 0; i < __items.length; i++) {
                    var __record = __items[i] || {};
                    number_of_received += __record.number_of_received;
                    number_of_sent += __record.number_of_sent;
                }
            } else {
                number_of_received = __data.total_of_received || 0;
                number_of_sent = __data.total_of_sent || 0;
            }
            $('#knobstick-number-of-received').text(number_of_received);
            $('#knobstick-number-of-sent').text(number_of_sent);
            return __items;
        }
    });

    onResize();

});
