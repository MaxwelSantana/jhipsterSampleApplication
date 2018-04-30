/* tslint:disable max-line-length */
import { ComponentFixture, TestBed, async } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs/Observable';

import { JhipsterSampleApplicationTestModule } from '../../../test.module';
import { MapeamentoDetailComponent } from '../../../../../../main/webapp/app/entities/mapeamento/mapeamento-detail.component';
import { MapeamentoService } from '../../../../../../main/webapp/app/entities/mapeamento/mapeamento.service';
import { Mapeamento } from '../../../../../../main/webapp/app/entities/mapeamento/mapeamento.model';

describe('Component Tests', () => {

    describe('Mapeamento Management Detail Component', () => {
        let comp: MapeamentoDetailComponent;
        let fixture: ComponentFixture<MapeamentoDetailComponent>;
        let service: MapeamentoService;

        beforeEach(async(() => {
            TestBed.configureTestingModule({
                imports: [JhipsterSampleApplicationTestModule],
                declarations: [MapeamentoDetailComponent],
                providers: [
                    MapeamentoService
                ]
            })
            .overrideTemplate(MapeamentoDetailComponent, '')
            .compileComponents();
        }));

        beforeEach(() => {
            fixture = TestBed.createComponent(MapeamentoDetailComponent);
            comp = fixture.componentInstance;
            service = fixture.debugElement.injector.get(MapeamentoService);
        });

        describe('OnInit', () => {
            it('Should call load all on init', () => {
                // GIVEN

                spyOn(service, 'find').and.returnValue(Observable.of(new HttpResponse({
                    body: new Mapeamento(123)
                })));

                // WHEN
                comp.ngOnInit();

                // THEN
                expect(service.find).toHaveBeenCalledWith(123);
                expect(comp.mapeamento).toEqual(jasmine.objectContaining({id: 123}));
            });
        });
    });

});
