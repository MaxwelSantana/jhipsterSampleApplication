/* tslint:disable max-line-length */
import { ComponentFixture, TestBed, async } from '@angular/core/testing';
import { Observable } from 'rxjs/Observable';
import { HttpHeaders, HttpResponse } from '@angular/common/http';

import { JhipsterSampleApplicationTestModule } from '../../../test.module';
import { TransmissaoComponent } from '../../../../../../main/webapp/app/entities/transmissao/transmissao.component';
import { TransmissaoService } from '../../../../../../main/webapp/app/entities/transmissao/transmissao.service';
import { Transmissao } from '../../../../../../main/webapp/app/entities/transmissao/transmissao.model';

describe('Component Tests', () => {

    describe('Transmissao Management Component', () => {
        let comp: TransmissaoComponent;
        let fixture: ComponentFixture<TransmissaoComponent>;
        let service: TransmissaoService;

        beforeEach(async(() => {
            TestBed.configureTestingModule({
                imports: [JhipsterSampleApplicationTestModule],
                declarations: [TransmissaoComponent],
                providers: [
                    TransmissaoService
                ]
            })
            .overrideTemplate(TransmissaoComponent, '')
            .compileComponents();
        }));

        beforeEach(() => {
            fixture = TestBed.createComponent(TransmissaoComponent);
            comp = fixture.componentInstance;
            service = fixture.debugElement.injector.get(TransmissaoService);
        });

        describe('OnInit', () => {
            it('Should call load all on init', () => {
                // GIVEN
                const headers = new HttpHeaders().append('link', 'link;link');
                spyOn(service, 'query').and.returnValue(Observable.of(new HttpResponse({
                    body: [new Transmissao(123)],
                    headers
                })));

                // WHEN
                comp.ngOnInit();

                // THEN
                expect(service.query).toHaveBeenCalled();
                expect(comp.transmissaos[0]).toEqual(jasmine.objectContaining({id: 123}));
            });
        });
    });

});
