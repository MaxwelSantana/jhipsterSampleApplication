/* tslint:disable max-line-length */
import { ComponentFixture, TestBed, async } from '@angular/core/testing';
import { Observable } from 'rxjs/Observable';
import { HttpHeaders, HttpResponse } from '@angular/common/http';

import { JhipsterSampleApplicationTestModule } from '../../../test.module';
import { MotivoAlteracaoComponent } from '../../../../../../main/webapp/app/entities/motivo-alteracao/motivo-alteracao.component';
import { MotivoAlteracaoService } from '../../../../../../main/webapp/app/entities/motivo-alteracao/motivo-alteracao.service';
import { MotivoAlteracao } from '../../../../../../main/webapp/app/entities/motivo-alteracao/motivo-alteracao.model';

describe('Component Tests', () => {

    describe('MotivoAlteracao Management Component', () => {
        let comp: MotivoAlteracaoComponent;
        let fixture: ComponentFixture<MotivoAlteracaoComponent>;
        let service: MotivoAlteracaoService;

        beforeEach(async(() => {
            TestBed.configureTestingModule({
                imports: [JhipsterSampleApplicationTestModule],
                declarations: [MotivoAlteracaoComponent],
                providers: [
                    MotivoAlteracaoService
                ]
            })
            .overrideTemplate(MotivoAlteracaoComponent, '')
            .compileComponents();
        }));

        beforeEach(() => {
            fixture = TestBed.createComponent(MotivoAlteracaoComponent);
            comp = fixture.componentInstance;
            service = fixture.debugElement.injector.get(MotivoAlteracaoService);
        });

        describe('OnInit', () => {
            it('Should call load all on init', () => {
                // GIVEN
                const headers = new HttpHeaders().append('link', 'link;link');
                spyOn(service, 'query').and.returnValue(Observable.of(new HttpResponse({
                    body: [new MotivoAlteracao(123)],
                    headers
                })));

                // WHEN
                comp.ngOnInit();

                // THEN
                expect(service.query).toHaveBeenCalled();
                expect(comp.motivoAlteracaos[0]).toEqual(jasmine.objectContaining({id: 123}));
            });
        });
    });

});
