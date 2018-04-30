/* tslint:disable max-line-length */
import { ComponentFixture, TestBed, async } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs/Observable';

import { JhipsterSampleApplicationTestModule } from '../../../test.module';
import { StatusContagemDetailComponent } from '../../../../../../main/webapp/app/entities/status-contagem/status-contagem-detail.component';
import { StatusContagemService } from '../../../../../../main/webapp/app/entities/status-contagem/status-contagem.service';
import { StatusContagem } from '../../../../../../main/webapp/app/entities/status-contagem/status-contagem.model';

describe('Component Tests', () => {

    describe('StatusContagem Management Detail Component', () => {
        let comp: StatusContagemDetailComponent;
        let fixture: ComponentFixture<StatusContagemDetailComponent>;
        let service: StatusContagemService;

        beforeEach(async(() => {
            TestBed.configureTestingModule({
                imports: [JhipsterSampleApplicationTestModule],
                declarations: [StatusContagemDetailComponent],
                providers: [
                    StatusContagemService
                ]
            })
            .overrideTemplate(StatusContagemDetailComponent, '')
            .compileComponents();
        }));

        beforeEach(() => {
            fixture = TestBed.createComponent(StatusContagemDetailComponent);
            comp = fixture.componentInstance;
            service = fixture.debugElement.injector.get(StatusContagemService);
        });

        describe('OnInit', () => {
            it('Should call load all on init', () => {
                // GIVEN

                spyOn(service, 'find').and.returnValue(Observable.of(new HttpResponse({
                    body: new StatusContagem(123)
                })));

                // WHEN
                comp.ngOnInit();

                // THEN
                expect(service.find).toHaveBeenCalledWith(123);
                expect(comp.statusContagem).toEqual(jasmine.objectContaining({id: 123}));
            });
        });
    });

});
