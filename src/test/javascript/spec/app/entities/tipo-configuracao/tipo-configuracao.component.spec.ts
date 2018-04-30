/* tslint:disable max-line-length */
import { ComponentFixture, TestBed, async } from '@angular/core/testing';
import { Observable } from 'rxjs/Observable';
import { HttpHeaders, HttpResponse } from '@angular/common/http';

import { JhipsterSampleApplicationTestModule } from '../../../test.module';
import { TipoConfiguracaoComponent } from '../../../../../../main/webapp/app/entities/tipo-configuracao/tipo-configuracao.component';
import { TipoConfiguracaoService } from '../../../../../../main/webapp/app/entities/tipo-configuracao/tipo-configuracao.service';
import { TipoConfiguracao } from '../../../../../../main/webapp/app/entities/tipo-configuracao/tipo-configuracao.model';

describe('Component Tests', () => {

    describe('TipoConfiguracao Management Component', () => {
        let comp: TipoConfiguracaoComponent;
        let fixture: ComponentFixture<TipoConfiguracaoComponent>;
        let service: TipoConfiguracaoService;

        beforeEach(async(() => {
            TestBed.configureTestingModule({
                imports: [JhipsterSampleApplicationTestModule],
                declarations: [TipoConfiguracaoComponent],
                providers: [
                    TipoConfiguracaoService
                ]
            })
            .overrideTemplate(TipoConfiguracaoComponent, '')
            .compileComponents();
        }));

        beforeEach(() => {
            fixture = TestBed.createComponent(TipoConfiguracaoComponent);
            comp = fixture.componentInstance;
            service = fixture.debugElement.injector.get(TipoConfiguracaoService);
        });

        describe('OnInit', () => {
            it('Should call load all on init', () => {
                // GIVEN
                const headers = new HttpHeaders().append('link', 'link;link');
                spyOn(service, 'query').and.returnValue(Observable.of(new HttpResponse({
                    body: [new TipoConfiguracao(123)],
                    headers
                })));

                // WHEN
                comp.ngOnInit();

                // THEN
                expect(service.query).toHaveBeenCalled();
                expect(comp.tipoConfiguracaos[0]).toEqual(jasmine.objectContaining({id: 123}));
            });
        });
    });

});
