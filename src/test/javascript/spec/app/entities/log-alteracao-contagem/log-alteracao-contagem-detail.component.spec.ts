/* tslint:disable max-line-length */
import { ComponentFixture, TestBed, async } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs/Observable';

import { JhipsterSampleApplicationTestModule } from '../../../test.module';
import { LogAlteracaoContagemDetailComponent } from '../../../../../../main/webapp/app/entities/log-alteracao-contagem/log-alteracao-contagem-detail.component';
import { LogAlteracaoContagemService } from '../../../../../../main/webapp/app/entities/log-alteracao-contagem/log-alteracao-contagem.service';
import { LogAlteracaoContagem } from '../../../../../../main/webapp/app/entities/log-alteracao-contagem/log-alteracao-contagem.model';

describe('Component Tests', () => {

    describe('LogAlteracaoContagem Management Detail Component', () => {
        let comp: LogAlteracaoContagemDetailComponent;
        let fixture: ComponentFixture<LogAlteracaoContagemDetailComponent>;
        let service: LogAlteracaoContagemService;

        beforeEach(async(() => {
            TestBed.configureTestingModule({
                imports: [JhipsterSampleApplicationTestModule],
                declarations: [LogAlteracaoContagemDetailComponent],
                providers: [
                    LogAlteracaoContagemService
                ]
            })
            .overrideTemplate(LogAlteracaoContagemDetailComponent, '')
            .compileComponents();
        }));

        beforeEach(() => {
            fixture = TestBed.createComponent(LogAlteracaoContagemDetailComponent);
            comp = fixture.componentInstance;
            service = fixture.debugElement.injector.get(LogAlteracaoContagemService);
        });

        describe('OnInit', () => {
            it('Should call load all on init', () => {
                // GIVEN

                spyOn(service, 'find').and.returnValue(Observable.of(new HttpResponse({
                    body: new LogAlteracaoContagem(123)
                })));

                // WHEN
                comp.ngOnInit();

                // THEN
                expect(service.find).toHaveBeenCalledWith(123);
                expect(comp.logAlteracaoContagem).toEqual(jasmine.objectContaining({id: 123}));
            });
        });
    });

});
