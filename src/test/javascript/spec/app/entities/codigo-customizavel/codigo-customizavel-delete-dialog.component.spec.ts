/* tslint:disable max-line-length */
import { ComponentFixture, TestBed, async, inject, fakeAsync, tick } from '@angular/core/testing';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { Observable } from 'rxjs/Observable';
import { JhiEventManager } from 'ng-jhipster';

import { JhipsterSampleApplicationTestModule } from '../../../test.module';
import { CodigoCustomizavelDeleteDialogComponent } from '../../../../../../main/webapp/app/entities/codigo-customizavel/codigo-customizavel-delete-dialog.component';
import { CodigoCustomizavelService } from '../../../../../../main/webapp/app/entities/codigo-customizavel/codigo-customizavel.service';

describe('Component Tests', () => {

    describe('CodigoCustomizavel Management Delete Component', () => {
        let comp: CodigoCustomizavelDeleteDialogComponent;
        let fixture: ComponentFixture<CodigoCustomizavelDeleteDialogComponent>;
        let service: CodigoCustomizavelService;
        let mockEventManager: any;
        let mockActiveModal: any;

        beforeEach(async(() => {
            TestBed.configureTestingModule({
                imports: [JhipsterSampleApplicationTestModule],
                declarations: [CodigoCustomizavelDeleteDialogComponent],
                providers: [
                    CodigoCustomizavelService
                ]
            })
            .overrideTemplate(CodigoCustomizavelDeleteDialogComponent, '')
            .compileComponents();
        }));

        beforeEach(() => {
            fixture = TestBed.createComponent(CodigoCustomizavelDeleteDialogComponent);
            comp = fixture.componentInstance;
            service = fixture.debugElement.injector.get(CodigoCustomizavelService);
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
