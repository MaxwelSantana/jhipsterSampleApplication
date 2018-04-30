/* tslint:disable max-line-length */
import { ComponentFixture, TestBed, async } from '@angular/core/testing';
import { Observable } from 'rxjs/Observable';
import { HttpHeaders, HttpResponse } from '@angular/common/http';

import { JhipsterSampleApplicationTestModule } from '../../../test.module';
import { StatusContagemComponent } from '../../../../../../main/webapp/app/entities/status-contagem/status-contagem.component';
import { StatusContagemService } from '../../../../../../main/webapp/app/entities/status-contagem/status-contagem.service';
import { StatusContagem } from '../../../../../../main/webapp/app/entities/status-contagem/status-contagem.model';

describe('Component Tests', () => {

    describe('StatusContagem Management Component', () => {
        let comp: StatusContagemComponent;
        let fixture: ComponentFixture<StatusContagemComponent>;
        let service: StatusContagemService;

        beforeEach(async(() => {
            TestBed.configureTestingModule({
                imports: [JhipsterSampleApplicationTestModule],
                declarations: [StatusContagemComponent],
                providers: [
                    StatusContagemService
                ]
            })
            .overrideTemplate(StatusContagemComponent, '')
            .compileComponents();
        }));

        beforeEach(() => {
            fixture = TestBed.createComponent(StatusContagemComponent);
            comp = fixture.componentInstance;
            service = fixture.debugElement.injector.get(StatusContagemService);
        });

        describe('OnInit', () => {
            it('Should call load all on init', () => {
                // GIVEN
                const headers = new HttpHeaders().append('link', 'link;link');
                spyOn(service, 'query').and.returnValue(Observable.of(new HttpResponse({
                    body: [new StatusContagem(123)],
                    headers
                })));

                // WHEN
                comp.ngOnInit();

                // THEN
                expect(service.query).toHaveBeenCalled();
                expect(comp.statusContagems[0]).toEqual(jasmine.objectContaining({id: 123}));
            });
        });
    });

});
