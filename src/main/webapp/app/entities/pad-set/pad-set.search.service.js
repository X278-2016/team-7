(function() {
    'use strict';

    angular
        .module('team7App')
        .factory('PadSetSearch', PadSetSearch);

    PadSetSearch.$inject = ['$resource'];

    function PadSetSearch($resource) {
        var resourceUrl =  'api/_search/pad-sets/:id';

        return $resource(resourceUrl, {}, {
            'query': { method: 'GET', isArray: true}
        });
    }
})();
