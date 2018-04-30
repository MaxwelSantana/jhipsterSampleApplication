/* tslint:disable max-line-length */
import { ComponentFixture, TestBed, async, inject, fakeAsync, tick } from '@angular/core/testing';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { Observable } from 'rxjs/Observable';
import { JhiEventManager } from 'ng-jhipster';

import { JhipsterSampleApplicationTestModule } from '../../../test.module';
import { CampoCustomizavelCadastroDeleteDialogComponent } from '../../../../../../main/webapp/app/entities/campo-customizavel-cadastro/campo-customizavel-cadastro-delete-dialog.component';
import { CampoCustomizavelCadastroService } from '../../../../../../main/webapp/app/entities/campo-customizavel-cadastro/campo-customizavel-cadastro.service';

describe('Component Tests', () => {

    describe('CampoCustomizavelCadastro Management Delete Component', () => {
        let comp: CampoCustomizavelCadastroDeleteDialogComponent;
        let fixture: ComponentFixture<CampoCustomizavelCadastroDeleteDialogComponent>;
        let service: CampoCustomizavelCadastroService;
        let mockEventManager: any;
        let mockActiveModal: any;

        beforeEach(async(() => {
            TestBed.configureTestingModule({
                imports: [JhipsterSampleApplicationTestModule],
                declarations: [CampoCustomizavelCadastroDeleteDialogComponent],
                providers: [
                    CampoCustomizavelCadastroService
                ]
            })
            .overrideTemplate(CampoCustomizavelCadastroDeleteDialogComponent, '')
            .compileComponents();
        }));

        beforeEach(() => {
            fixture = TestBed.createComponent(CampoCustomizavelCadastroDeleteDialogComponent);
            comp = fixture.componentInstance;
            service = fixture.debugElement.injector.get(CampoCustomizavelCadastroService);
            mockEventManager = fixture.debugElement.injector.get(JhiEventManager);
            mockActiveModal = fixture.debugElement.injector.get(NgbActiveModal);
        });

        describe('confirmDelete', () => {
            it('Should call delete service on confirmDelete',
                inject([],
                    fakeAsync(() => {
                        // GIVEN
                        spyOn(service, 'delete').and.returnValue(Observable.of({}));

                        // WHEN
                        comp.confirmDelete(123);
                        tick();

                        // THEN
                        expect(service.delete).toHaveBeenCalledWith(123);
                        expect(mockActiveModal.dismissSpy).toHaveBeenCalled();
                        expect(mockEventManager.broadcastSpy).toHaveBeenCalled();
                    })
                )
            );
        });
    });

});
