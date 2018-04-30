/* tslint:disable max-line-length */
import { ComponentFixture, TestBed, async, inject, fakeAsync, tick } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { Observable } from 'rxjs/Observable';
import { JhiEventManager } from 'ng-jhipster';

import { JhipsterSampleApplicationTestModule } from '../../../test.module';
import { TransmissaoDialogComponent } from '../../../../../../main/webapp/app/entities/transmissao/transmissao-dialog.component';
import { TransmissaoService } from '../../../../../../main/webapp/app/entities/transmissao/transmissao.service';
import { Transmissao } from '../../../../../../main/webapp/app/entities/transmissao/transmissao.model';
import { InventarioService } from '../../../../../../main/webapp/app/entities/inventario';
import { FuncionarioService } from '../../../../../../main/webapp/app/entities/funcionario';
import { ColetorService } from '../../../../../../main/webapp/app/entities/coletor';
import { TipoContagemService } from '../../../../../../main/webapp/app/entities/tipo-contagem';

describe('Component Tests', () => {

    describe('Transmissao Management Dialog Component', () => {
        let comp: TransmissaoDialogComponent;
        let fixture: ComponentFixture<TransmissaoDialogComponent>;
        let service: TransmissaoService;
        let mockEventManager: any;
        let mockActiveModal: any;

        beforeEach(async(() => {
            TestBed.configureTestingModule({
                imports: [JhipsterSampleApplicationTestModule],
                declarations: [TransmissaoDialogComponent],
                providers: [
                    InventarioService,
                    FuncionarioService,
                    ColetorService,
                    TipoContagemService,
                    TransmissaoService
                ]
            })
            .overrideTemplate(TransmissaoDialogComponent, '')
            .compileComponents();
        }));

        beforeEach(() => {
            fixture = TestBed.createComponent(TransmissaoDialogComponent);
            comp = fixture.componentInstance;
            service = fixture.debugElement.injector.get(TransmissaoService);
            mockEventManager = fixture.debugElement.injector.get(JhiEventManager);
            mockActiveModal = fixture.debugElement.injector.get(NgbActiveModal);
        });

        describe('save', () => {
            it('Should call update service on save for existing entity',
                inject([],
                    fakeAsync(() => {
                        // GIVEN
                        const entity = new Transmissao(123);
                        spyOn(service, 'update').and.returnValue(Observable.of(new HttpResponse({body: entity})));
                        comp.transmissao = entity;
                        // WHEN
                        comp.save();
                        tick(); // simulate async

                        // THEN
                        expect(service.update).toHaveBeenCalledWith(entity);
                        expect(comp.isSaving).toEqual(false);
                        expect(mockEventManager.broadcastSpy).toHaveBeenCalledWith({ name: 'transmissaoListModification', content: 'OK'});
                        expect(mockActiveModal.dismissSpy).toHaveBeenCalled();
                    })
                )
            );

            it('Should call create service on save for new entity',
                inject([],
                    fakeAsync(() => {
                        // GIVEN
                        const entity = new Transmissao();
                        spyOn(service, 'create').and.returnValue(Observable.of(new HttpResponse({body: entity})));
                        comp.transmissao = entity;
                        // WHEN
                        comp.save();
                        tick(); // simulate async

                        // THEN
                        expect(service.create).toHaveBeenCalledWith(entity);
                        expect(comp.isSaving).toEqual(false);
                        expect(mockEventManager.broadcastSpy).toHaveBeenCalledWith({ name: 'transmissaoListModification', content: 'OK'});
                        expect(mockActiveModal.dismissSpy).toHaveBeenCalled();
                    })
                )
            );
        });
    });

});
