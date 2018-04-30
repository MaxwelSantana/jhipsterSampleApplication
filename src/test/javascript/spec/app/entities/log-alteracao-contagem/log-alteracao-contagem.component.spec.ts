/* tslint:disable max-line-length */
import { ComponentFixture, TestBed, async } from '@angular/core/testing';
import { Observable } from 'rxjs/Observable';
import { HttpHeaders, HttpResponse } from '@angular/common/http';

import { JhipsterSampleApplicationTestModule } from '../../../test.module';
import { LogAlteracaoContagemComponent } from '../../../../../../main/webapp/app/entities/log-alteracao-contagem/log-alteracao-contagem.component';
import { LogAlteracaoContagemService } from '../../../../../../main/webapp/app/entities/log-alteracao-contagem/log-alteracao-contagem.service';
import { LogAlteracaoContagem } from '../../../../../../main/webapp/app/entities/log-alteracao-contagem/log-alteracao-contagem.model';

describe('Component Tests', () => {

    describe('LogAlteracaoContagem Management Component', () => {
        let comp: LogAlteracaoContagemComponent;
        let fixture: ComponentFixture<LogAlteracaoContagemComponent>;
        let service: LogAlteracaoContagemService;

        beforeEach(async(() => {
            TestBed.configureTestingModule({
                imports: [JhipsterSampleApplicationTestModule],
                declarations: [LogAlteracaoContagemComponent],
                providers: [
                    LogAlteracaoContagemService
                ]
            })
            .overrideTemplate(LogAlteracaoContagemComponent, '')
            .compileComponents();
        }));

        beforeEach(() => {
            fixture = TestBed.createComponent(LogAlteracaoContagemComponent);
            comp = fixture.componentInstance;
            service = fixture.debugElement.injector.get(LogAlteracaoContagemService);
        });

        describe('OnInit', () => {
            it('Should call load all on init', () => {
                // GIVEN
                const headers = new HttpHeaders().append('link', 'link;link');
                spyOn(service, 'query').and.returnValue(Observable.of(new HttpResponse({
                    body: [new LogAlteracaoContagem(123)],
                    headers
                })));

                // WHEN
                comp.ngOnInit();

                // THEN
                expect(service.query).toHaveBeenCalled();
                expect(comp.logAlteracaoContagems[0]).toEqual(jasmine.objectContaining({id: 123}));
            });
        });
    });

});
