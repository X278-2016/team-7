(function() {
    'use strict';

    angular
        .module('team7App')
        .factory('MetricSearch', MetricSearch);

    MetricSearch.$inject = ['$resource'];

    function MetricSearch($resource) {
        var resourceUrl =  'api/_search/metrics/:id';

        return $resource(resourceUrl, {}, {
            'query': { method: 'GET', isArray: true}
        });
    }
})();
