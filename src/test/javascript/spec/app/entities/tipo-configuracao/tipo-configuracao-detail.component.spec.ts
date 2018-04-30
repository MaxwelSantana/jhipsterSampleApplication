/* tslint:disable max-line-length */
import { ComponentFixture, TestBed, async } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs/Observable';

import { JhipsterSampleApplicationTestModule } from '../../../test.module';
import { TipoConfiguracaoDetailComponent } from '../../../../../../main/webapp/app/entities/tipo-configuracao/tipo-configuracao-detail.component';
import { TipoConfiguracaoService } from '../../../../../../main/webapp/app/entities/tipo-configuracao/tipo-configuracao.service';
import { TipoConfiguracao } from '../../../../../../main/webapp/app/entities/tipo-configuracao/tipo-configuracao.model';

describe('Component Tests', () => {

    describe('TipoConfiguracao Management Detail Component', () => {
        let comp: TipoConfiguracaoDetailComponent;
        let fixture: ComponentFixture<TipoConfiguracaoDetailComponent>;
        let service: TipoConfiguracaoService;

        beforeEach(async(() => {
            TestBed.configureTestingModule({
                imports: [JhipsterSampleApplicationTestModule],
                declarations: [TipoConfiguracaoDetailComponent],
                providers: [
                    TipoConfiguracaoService
                ]
            })
            .overrideTemplate(TipoConfiguracaoDetailComponent, '')
            .compileComponents();
        }));

        beforeEach(() => {
            fixture = TestBed.createComponent(TipoConfiguracaoDetailComponent);
            comp = fixture.componentInstance;
            service = fixture.debugElement.injector.get(TipoConfiguracaoService);
        });

        describe('OnInit', () => {
            it('Should call load all on init', () => {
                // GIVEN

                spyOn(service, 'find').and.returnValue(Observable.of(new HttpResponse({
                    body: new TipoConfiguracao(123)
                })));

                // WHEN
                comp.ngOnInit();

                // THEN
                expect(service.find).toHaveBeenCalledWith(123);
                expect(comp.tipoConfiguracao).toEqual(jasmine.objectContaining({id: 123}));
            });
        });
    });

});
