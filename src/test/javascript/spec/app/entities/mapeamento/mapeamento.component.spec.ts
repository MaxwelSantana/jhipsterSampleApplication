/* tslint:disable max-line-length */
import { ComponentFixture, TestBed, async } from '@angular/core/testing';
import { Observable } from 'rxjs/Observable';
import { HttpHeaders, HttpResponse } from '@angular/common/http';

import { JhipsterSampleApplicationTestModule } from '../../../test.module';
import { MapeamentoComponent } from '../../../../../../main/webapp/app/entities/mapeamento/mapeamento.component';
import { MapeamentoService } from '../../../../../../main/webapp/app/entities/mapeamento/mapeamento.service';
import { Mapeamento } from '../../../../../../main/webapp/app/entities/mapeamento/mapeamento.model';

describe('Component Tests', () => {

    describe('Mapeamento Management Component', () => {
        let comp: MapeamentoComponent;
        let fixture: ComponentFixture<MapeamentoComponent>;
        let service: MapeamentoService;

        beforeEach(async(() => {
            TestBed.configureTestingModule({
                imports: [JhipsterSampleApplicationTestModule],
                declarations: [MapeamentoComponent],
                providers: [
                    MapeamentoService
                ]
            })
            .overrideTemplate(MapeamentoComponent, '')
            .compileComponents();
        }));

        beforeEach(() => {
            fixture = TestBed.createComponent(MapeamentoComponent);
            comp = fixture.componentInstance;
            service = fixture.debugElement.injector.get(MapeamentoService);
        });

        describe('OnInit', () => {
            it('Should call load all on init', () => {
                // GIVEN
                const headers = new HttpHeaders().append('link', 'link;link');
                spyOn(service, 'query').and.returnValue(Observable.of(new HttpResponse({
                    body: [new Mapeamento(123)],
                    headers
                })));

                // WHEN
                comp.ngOnInit();

                // THEN
                expect(service.query).toHaveBeenCalled();
                expect(comp.mapeamentos[0]).toEqual(jasmine.objectContaining({id: 123}));
            });
        });
    });

});
