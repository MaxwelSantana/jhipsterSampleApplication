/* tslint:disable max-line-length */
import { ComponentFixture, TestBed, async } from '@angular/core/testing';
import { Observable } from 'rxjs/Observable';
import { HttpHeaders, HttpResponse } from '@angular/common/http';

import { JhipsterSampleApplicationTestModule } from '../../../test.module';
import { TipoContagemComponent } from '../../../../../../main/webapp/app/entities/tipo-contagem/tipo-contagem.component';
import { TipoContagemService } from '../../../../../../main/webapp/app/entities/tipo-contagem/tipo-contagem.service';
import { TipoContagem } from '../../../../../../main/webapp/app/entities/tipo-contagem/tipo-contagem.model';

describe('Component Tests', () => {

    describe('TipoContagem Management Component', () => {
        let comp: TipoContagemComponent;
        let fixture: ComponentFixture<TipoContagemComponent>;
        let service: TipoContagemService;

        beforeEach(async(() => {
            TestBed.configureTestingModule({
                imports: [JhipsterSampleApplicationTestModule],
                declarations: [TipoContagemComponent],
                providers: [
                    TipoContagemService
                ]
            })
            .overrideTemplate(TipoContagemComponent, '')
            .compileComponents();
        }));

        beforeEach(() => {
            fixture = TestBed.createComponent(TipoContagemComponent);
            comp = fixture.componentInstance;
            service = fixture.debugElement.injector.get(TipoContagemService);
        });

        describe('OnInit', () => {
            it('Should call load all on init', () => {
                // GIVEN
                const headers = new HttpHeaders().append('link', 'link;link');
                spyOn(service, 'query').and.returnValue(Observable.of(new HttpResponse({
                    body: [new TipoContagem(123)],
                    headers
                })));

                // WHEN
                comp.ngOnInit();

                // THEN
                expect(service.query).toHaveBeenCalled();
                expect(comp.tipoContagems[0]).toEqual(jasmine.objectContaining({id: 123}));
            });
        });
    });

});
