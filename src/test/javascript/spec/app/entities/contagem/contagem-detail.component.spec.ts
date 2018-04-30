/* tslint:disable max-line-length */
import { ComponentFixture, TestBed, async } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs/Observable';

import { JhipsterSampleApplicationTestModule } from '../../../test.module';
import { ContagemDetailComponent } from '../../../../../../main/webapp/app/entities/contagem/contagem-detail.component';
import { ContagemService } from '../../../../../../main/webapp/app/entities/contagem/contagem.service';
import { Contagem } from '../../../../../../main/webapp/app/entities/contagem/contagem.model';

describe('Component Tests', () => {

    describe('Contagem Management Detail Component', () => {
        let comp: ContagemDetailComponent;
        let fixture: ComponentFixture<ContagemDetailComponent>;
        let service: ContagemService;

        beforeEach(async(() => {
            TestBed.configureTestingModule({
                imports: [JhipsterSampleApplicationTestModule],
                declarations: [ContagemDetailComponent],
                providers: [
                    ContagemService
                ]
            })
            .overrideTemplate(ContagemDetailComponent, '')
            .compileComponents();
        }));

        beforeEach(() => {
            fixture = TestBed.createComponent(ContagemDetailComponent);
            comp = fixture.componentInstance;
            service = fixture.debugElement.injector.get(ContagemService);
        });

        describe('OnInit', () => {
            it('Should call load all on init', () => {
                // GIVEN

                spyOn(service, 'find').and.returnValue(Observable.of(new HttpResponse({
                    body: new Contagem(123)
                })));

                // WHEN
                comp.ngOnInit();

                // THEN
                expect(service.find).toHaveBeenCalledWith(123);
                expect(comp.contagem).toEqual(jasmine.objectContaining({id: 123}));
            });
        });
    });

});
