package com.patient.service.graphql;

import com.patient.domain.model.Doc;
import com.patient.domain.model.Title;
import com.patient.domain.model.graphInput.DocInput;
import com.patient.repository.DocRepo;
import com.patient.repository.PatientRepo;
import org.easymock.EasyMock;
import org.easymock.EasyMockExtension;
import org.easymock.Mock;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

@ExtendWith(EasyMockExtension.class)
public class TestDocService {

    @Mock
    private DocRepo mockRepo;

    @Test
    public void test_createNewDoctor_success(){

//        DocRepo mockRepo = EasyMock.createMock(DocRepo.class);
        DocService service = new DocService();
        service.setDocRepo(mockRepo);

        Doc mockDoc = new Doc();
        mockDoc.setFirstName("test");
        mockDoc.setLastName("doc");
        mockDoc.setSpecialty("internal");
        mockDoc.setTitle(Title.MD);

        EasyMock.expect(mockRepo.save(mockDoc))
                .andReturn(mockDoc);
        EasyMock.replay(mockRepo);

        service.saveDoctor(mockDoc);

        EasyMock.verify(mockRepo);
    }

    @Test
    public void test_updateDoctor_success() {
        DocService service = new DocService();
        service.setDocRepo(mockRepo);

        Doc mockDoc = EasyMock.createMock(Doc.class);

        Doc existingMocDoc = new Doc();
        existingMocDoc.setFirstName("test");
        existingMocDoc.setLastName("doc");
        existingMocDoc.setSpecialty("internal");
        existingMocDoc.setTitle(Title.MD);

        DocInput docInput = new DocInput();
        docInput.setFirstName("updated");
        docInput.setLastName("updated");
        docInput.setSpecialty("updated");
        docInput.setTitle(Title.DC);

        EasyMock.expect(mockRepo.save(existingMocDoc))
                .andReturn(existingMocDoc);
        EasyMock.replay(mockRepo);

        service.updateDoctor(existingMocDoc, docInput);

        Assertions.assertEquals(existingMocDoc.getFirstName(), docInput.getFirstName());
        Assertions.assertEquals(existingMocDoc.getLastName(), docInput.getLastName());
        Assertions.assertEquals(existingMocDoc.getTitle(), docInput.getTitle());
        Assertions.assertEquals(existingMocDoc.getSpecialty(), docInput.getSpecialty());

        EasyMock.verify(mockRepo);
    }

    @Test
    public void test_updateDoctor_one_field_success() {
        DocService service = new DocService();
        service.setDocRepo(mockRepo);

        Doc mockDoc = EasyMock.createMock(Doc.class);

        String firstName = "test";
        String lastName = "doc";
        String specialty = "internal";
        Title title = Title.MD;

        Doc existingMocDoc = new Doc();
        existingMocDoc.setFirstName(firstName);
        existingMocDoc.setLastName(lastName);
        existingMocDoc.setSpecialty(specialty);
        existingMocDoc.setTitle(title);

        DocInput docInput = new DocInput();
        docInput.setFirstName("updated");

        EasyMock.expect(mockRepo.save(existingMocDoc))
                .andReturn(existingMocDoc);
        EasyMock.replay(mockRepo);

        service.updateDoctor(existingMocDoc, docInput);

        Assertions.assertEquals(existingMocDoc.getFirstName(), docInput.getFirstName());
        Assertions.assertEquals(existingMocDoc.getLastName(), lastName);
        Assertions.assertEquals(existingMocDoc.getTitle(), title);
        Assertions.assertEquals(existingMocDoc.getSpecialty(), specialty);

        EasyMock.verify(mockRepo);
    }

}
