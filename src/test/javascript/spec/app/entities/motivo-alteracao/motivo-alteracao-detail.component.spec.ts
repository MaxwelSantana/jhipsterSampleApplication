/* tslint:disable max-line-length */
import { ComponentFixture, TestBed, async } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs/Observable';

import { JhipsterSampleApplicationTestModule } from '../../../test.module';
import { MotivoAlteracaoDetailComponent } from '../../../../../../main/webapp/app/entities/motivo-alteracao/motivo-alteracao-detail.component';
import { MotivoAlteracaoService } from '../../../../../../main/webapp/app/entities/motivo-alteracao/motivo-alteracao.service';
import { MotivoAlteracao } from '../../../../../../main/webapp/app/entities/motivo-alteracao/motivo-alteracao.model';

describe('Component Tests', () => {

    describe('MotivoAlteracao Management Detail Component', () => {
        let comp: MotivoAlteracaoDetailComponent;
        let fixture: ComponentFixture<MotivoAlteracaoDetailComponent>;
        let service: MotivoAlteracaoService;

        beforeEach(async(() => {
            TestBed.configureTestingModule({
                imports: [JhipsterSampleApplicationTestModule],
                declarations: [MotivoAlteracaoDetailComponent],
                providers: [
                    MotivoAlteracaoService
                ]
            })
            .overrideTemplate(MotivoAlteracaoDetailComponent, '')
            .compileComponents();
        }));

        beforeEach(() => {
            fixture = TestBed.createComponent(MotivoAlteracaoDetailComponent);
            comp = fixture.componentInstance;
            service = fixture.debugElement.injector.get(MotivoAlteracaoService);
        });

        describe('OnInit', () => {
            it('Should call load all on init', () => {
                // GIVEN

                spyOn(service, 'find').and.returnValue(Observable.of(new HttpResponse({
                    body: new MotivoAlteracao(123)
                })));

                // WHEN
                comp.ngOnInit();

                // THEN
                expect(service.find).toHaveBeenCalledWith(123);
                expect(comp.motivoAlteracao).toEqual(jasmine.objectContaining({id: 123}));
            });
        });
    });

});
