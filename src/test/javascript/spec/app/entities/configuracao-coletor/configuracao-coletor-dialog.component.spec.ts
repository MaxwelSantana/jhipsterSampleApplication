/* tslint:disable max-line-length */
import { ComponentFixture, TestBed, async, inject, fakeAsync, tick } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { Observable } from 'rxjs/Observable';
import { JhiEventManager } from 'ng-jhipster';

import { JhipsterSampleApplicationTestModule } from '../../../test.module';
import { ConfiguracaoColetorDialogComponent } from '../../../../../../main/webapp/app/entities/configuracao-coletor/configuracao-coletor-dialog.component';
import { ConfiguracaoColetorService } from '../../../../../../main/webapp/app/entities/configuracao-coletor/configuracao-coletor.service';
import { ConfiguracaoColetor } from '../../../../../../main/webapp/app/entities/configuracao-coletor/configuracao-coletor.model';
import { ClienteService } from '../../../../../../main/webapp/app/entities/cliente';
import { CodigoCustomizavelService } from '../../../../../../main/webapp/app/entities/codigo-customizavel';

describe('Component Tests', () => {

    describe('ConfiguracaoColetor Management Dialog Component', () => {
        let comp: ConfiguracaoColetorDialogComponent;
        let fixture: ComponentFixture<ConfiguracaoColetorDialogComponent>;
        let service: ConfiguracaoColetorService;
        let mockEventManager: any;
        let mockActiveModal: any;

        beforeEach(async(() => {
            TestBed.configureTestingModule({
                imports: [JhipsterSampleApplicationTestModule],
                declarations: [ConfiguracaoColetorDialogComponent],
                providers: [
                    ClienteService,
                    CodigoCustomizavelService,
                    ConfiguracaoColetorService
                ]
            })
            .overrideTemplate(ConfiguracaoColetorDialogComponent, '')
            .compileComponents();
        }));

        beforeEach(() => {
            fixture = TestBed.createComponent(ConfiguracaoColetorDialogComponent);
            comp = fixture.componentInstance;
            service = fixture.debugElement.injector.get(ConfiguracaoColetorService);
            mockEventManager = fixture.debugElement.injector.get(JhiEventManager);
            mockActiveModal = fixture.debugElement.injector.get(NgbActiveModal);
        });

        describe('save', () => {
            it('Should call update service on save for existing entity',
                inject([],
                    fakeAsync(() => {
                        // GIVEN
                        const entity = new ConfiguracaoColetor(123);
                        spyOn(service, 'update').and.returnValue(Observable.of(new HttpResponse({body: entity})));
                        comp.configuracaoColetor = entity;
                        // WHEN
                        comp.save();
                        tick(); // simulate async

                        // THEN
                        expect(service.update).toHaveBeenCalledWith(entity);
                        expect(comp.isSaving).toEqual(false);
                        expect(mockEventManager.broadcastSpy).toHaveBeenCalledWith({ name: 'configuracaoColetorListModification', content: 'OK'});
                        expect(mockActiveModal.dismissSpy).toHaveBeenCalled();
                    })
                )
            );

            it('Should call create service on save for new entity',
                inject([],
                    fakeAsync(() => {
                        // GIVEN
                        const entity = new ConfiguracaoColetor();
                        spyOn(service, 'create').and.returnValue(Observable.of(new HttpResponse({body: entity})));
                        comp.configuracaoColetor = entity;
                        // WHEN
                        comp.save();
                        tick(); // simulate async

                        // THEN
                        expect(service.create).toHaveBeenCalledWith(entity);
                        expect(comp.isSaving).toEqual(false);
                        expect(mockEventManager.broadcastSpy).toHaveBeenCalledWith({ name: 'configuracaoColetorListModification', content: 'OK'});
                        expect(mockActiveModal.dismissSpy).toHaveBeenCalled();
                    })
                )
            );
        });
    });

});
