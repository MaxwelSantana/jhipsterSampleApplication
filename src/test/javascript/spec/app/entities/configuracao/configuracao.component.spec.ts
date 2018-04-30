/* tslint:disable max-line-length */
import { ComponentFixture, TestBed, async } from '@angular/core/testing';
import { Observable } from 'rxjs/Observable';
import { HttpHeaders, HttpResponse } from '@angular/common/http';

import { JhipsterSampleApplicationTestModule } from '../../../test.module';
import { ConfiguracaoComponent } from '../../../../../../main/webapp/app/entities/configuracao/configuracao.component';
import { ConfiguracaoService } from '../../../../../../main/webapp/app/entities/configuracao/configuracao.service';
import { Configuracao } from '../../../../../../main/webapp/app/entities/configuracao/configuracao.model';

describe('Component Tests', () => {

    describe('Configuracao Management Component', () => {
        let comp: ConfiguracaoComponent;
        let fixture: ComponentFixture<ConfiguracaoComponent>;
        let service: ConfiguracaoService;

        beforeEach(async(() => {
            TestBed.configureTestingModule({
                imports: [JhipsterSampleApplicationTestModule],
                declarations: [ConfiguracaoComponent],
                providers: [
                    ConfiguracaoService
                ]
            })
            .overrideTemplate(ConfiguracaoComponent, '')
            .compileComponents();
        }));

        beforeEach(() => {
            fixture = TestBed.createComponent(ConfiguracaoComponent);
            comp = fixture.componentInstance;
            service = fixture.debugElement.injector.get(ConfiguracaoService);
        });

        describe('OnInit', () => {
            it('Should call load all on init', () => {
                // GIVEN
                const headers = new HttpHeaders().append('link', 'link;link');
                spyOn(service, 'query').and.returnValue(Observable.of(new HttpResponse({
                    body: [new Configuracao(123)],
                    headers
                })));

                // WHEN
                comp.ngOnInit();

                // THEN
                expect(service.query).toHaveBeenCalled();
                expect(comp.configuracaos[0]).toEqual(jasmine.objectContaining({id: 123}));
            });
        });
    });

});
