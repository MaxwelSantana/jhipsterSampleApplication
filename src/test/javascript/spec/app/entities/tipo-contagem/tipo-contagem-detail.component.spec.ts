/* tslint:disable max-line-length */
import { ComponentFixture, TestBed, async } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs/Observable';

import { JhipsterSampleApplicationTestModule } from '../../../test.module';
import { TipoContagemDetailComponent } from '../../../../../../main/webapp/app/entities/tipo-contagem/tipo-contagem-detail.component';
import { TipoContagemService } from '../../../../../../main/webapp/app/entities/tipo-contagem/tipo-contagem.service';
import { TipoContagem } from '../../../../../../main/webapp/app/entities/tipo-contagem/tipo-contagem.model';

describe('Component Tests', () => {

    describe('TipoContagem Management Detail Component', () => {
        let comp: TipoContagemDetailComponent;
        let fixture: ComponentFixture<TipoContagemDetailComponent>;
        let service: TipoContagemService;

        beforeEach(async(() => {
            TestBed.configureTestingModule({
                imports: [JhipsterSampleApplicationTestModule],
                declarations: [TipoContagemDetailComponent],
                providers: [
                    TipoContagemService
                ]
            })
            .overrideTemplate(TipoContagemDetailComponent, '')
            .compileComponents();
        }));

        beforeEach(() => {
            fixture = TestBed.createComponent(TipoContagemDetailComponent);
            comp = fixture.componentInstance;
            service = fixture.debugElement.injector.get(TipoContagemService);
        });

        describe('OnInit', () => {
            it('Should call load all on init', () => {
                // GIVEN

                spyOn(service, 'find').and.returnValue(Observable.of(new HttpResponse({
                    body: new TipoContagem(123)
                })));

                // WHEN
                comp.ngOnInit();

                // THEN
                expect(service.find).toHaveBeenCalledWith(123);
                expect(comp.tipoContagem).toEqual(jasmine.objectContaining({id: 123}));
            });
        });
    });

});
