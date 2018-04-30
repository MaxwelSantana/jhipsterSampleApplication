/* tslint:disable max-line-length */
import { ComponentFixture, TestBed, async } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs/Observable';

import { JhipsterSampleApplicationTestModule } from '../../../test.module';
import { ConfiguracaoDetailComponent } from '../../../../../../main/webapp/app/entities/configuracao/configuracao-detail.component';
import { ConfiguracaoService } from '../../../../../../main/webapp/app/entities/configuracao/configuracao.service';
import { Configuracao } from '../../../../../../main/webapp/app/entities/configuracao/configuracao.model';

describe('Component Tests', () => {

    describe('Configuracao Management Detail Component', () => {
        let comp: ConfiguracaoDetailComponent;
        let fixture: ComponentFixture<ConfiguracaoDetailComponent>;
        let service: ConfiguracaoService;

        beforeEach(async(() => {
            TestBed.configureTestingModule({
                imports: [JhipsterSampleApplicationTestModule],
                declarations: [ConfiguracaoDetailComponent],
                providers: [
                    ConfiguracaoService
                ]
            })
            .overrideTemplate(ConfiguracaoDetailComponent, '')
            .compileComponents();
        }));

        beforeEach(() => {
            fixture = TestBed.createComponent(ConfiguracaoDetailComponent);
            comp = fixture.componentInstance;
            service = fixture.debugElement.injector.get(ConfiguracaoService);
        });

        describe('OnInit', () => {
            it('Should call load all on init', () => {
                // GIVEN

                spyOn(service, 'find').and.returnValue(Observable.of(new HttpResponse({
                    body: new Configuracao(123)
                })));

                // WHEN
                comp.ngOnInit();

                // THEN
                expect(service.find).toHaveBeenCalledWith(123);
                expect(comp.configuracao).toEqual(jasmine.objectContaining({id: 123}));
            });
        });
    });

});
