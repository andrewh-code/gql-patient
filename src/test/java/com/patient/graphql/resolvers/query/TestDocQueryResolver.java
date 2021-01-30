package com.patient.graphql.resolvers.query;

import com.patient.domain.model.Doc;
import com.patient.service.graphql.DocServiceImpl;
import graphql.GraphQLException;
import org.easymock.EasyMock;
import org.easymock.EasyMockExtension;
import org.easymock.Mock;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import java.util.Arrays;
import java.util.List;

@ExtendWith(EasyMockExtension.class)
public class TestDocQueryResolver {

    @Mock
    private DocServiceImpl docService;


    @Test
    public void test_retrieveAllDocs_success() throws Exception {

        Doc d1 = Doc.docBuilder()
                .firstName("new")
                .lastName("doc1")
                .build();
        Doc d2 = Doc.docBuilder()
                .firstName("new")
                .lastName("doc2")
                .build();
        List<Doc> docs = Arrays.asList(d1, d2);

        DocQueryResolver resolver = new DocQueryResolver();
        resolver.setDocService(docService);

        EasyMock.expect(docService.retrieveAllDoctors()).andReturn(docs);
        EasyMock.replay(docService);

        List<Doc> newdocs = resolver.retrieveAllDoctors();

        EasyMock.verify(docService);
    }

    @Test
    public void test_retrieveAllDocs_fail_throws_gqlException() throws Exception {

        Doc d1 = Doc.docBuilder()
                .firstName("new")
                .lastName("doc1")
                .build();
        Doc d2 = Doc.docBuilder()
                .firstName("new")
                .lastName("doc2")
                .build();
        List<Doc> docs = Arrays.asList(d1, d2);

        DocQueryResolver resolver = new DocQueryResolver();
        resolver.setDocService(docService);

        EasyMock.expect(docService.retrieveAllDoctors()).andThrow(new Exception("error"));
        EasyMock.replay(docService);

        try {
            resolver.retrieveAllDoctors();
            Assertions.fail("expected to throw");
        }catch(GraphQLException e){
            Assertions.assertEquals("error", e.getMessage());
        }

        EasyMock.verify(docService);
    }


}
