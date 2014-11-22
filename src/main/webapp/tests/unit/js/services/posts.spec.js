describe('Posts service', function() {
    var response = [
        {
            "is_deleted":false,
            "post_id":1,
            "title":"title1",
            "message":"clob4: 'message1'",
            "author_id":2,
            "create_date":null,
            "modified_date":null
        },
        {
            "is_deleted":false,
            "post_id":2,
            "title":"title2",
            "message":"clob5: 'message2'",
            "author_id":3,
            "create_date":null,
            "modified_date":null
        },
        {
            "is_deleted":false,
            "post_id":3,
            "title":"title3",
            "message":"clob6: 'message3'",
            "author_id":4,
            "create_date":null,
            "modified_date":null
        },
        {
            "is_deleted":false,
            "post_id":4,
            "title":"title4",
            "message":"clob7: 'message4'",
            "author_id":4,
            "create_date":null,
            "modified_date":null
        }
    ]

    beforeEach(function() {
        module('blogWebApp', function($provide) {
            $provide.value();
        });

        inject(function($injector) {
            injector = $injector;
            $httpBackend = $injector.get('$httpBackend');
            $httpBackend.when('GET', 'service/data/posts').respond(response);
             });
    });

    afterEach(function() {
        $httpBackend.verifyNoOutstandingExpectation();
        $httpBackend.verifyNoOutstandingRequest();
    });

    it('calls service/data/posts', function() {
        $httpBackend.expectGET('service/data/posts');
        injector.get('Posts');
        $httpBackend.flush();
    });


    it('returns post by id', function() {
        var posts = injector.get('Posts');
        $httpBackend.flush();
        expect(posts.get(1)).toEqual(response[0]);
    });

    it('getAll method returns all posts', function() {
        var posts = injector.get('Posts');
        $httpBackend.flush();
        expect(posts.getAll()).toEqual(response);
    });


});