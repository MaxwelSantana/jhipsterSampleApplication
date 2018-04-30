/* tslint:disable max-line-length */
import { ComponentFixture, TestBed, async } from '@angular/core/testing';
import { Observable } from 'rxjs/Observable';
import { HttpHeaders, HttpResponse } from '@angular/common/http';

import { JhipsterSampleApplicationTestModule } from '../../../test.module';
import { ConfiguracaoColetorComponent } from '../../../../../../main/webapp/app/entities/configuracao-coletor/configuracao-coletor.component';
import { ConfiguracaoColetorService } from '../../../../../../main/webapp/app/entities/configuracao-coletor/configuracao-coletor.service';
import { ConfiguracaoColetor } from '../../../../../../main/webapp/app/entities/configuracao-coletor/configuracao-coletor.model';

describe('Component Tests', () => {

    describe('ConfiguracaoColetor Management Component', () => {
        let comp: ConfiguracaoColetorComponent;
        let fixture: ComponentFixture<ConfiguracaoColetorComponent>;
        let service: ConfiguracaoColetorService;

        beforeEach(async(() => {
            TestBed.configureTestingModule({
                imports: [JhipsterSampleApplicationTestModule],
                declarations: [ConfiguracaoColetorComponent],
                providers: [
                    ConfiguracaoColetorService
                ]
            })
            .overrideTemplate(ConfiguracaoColetorComponent, '')
            .compileComponents();
        }));

        beforeEach(() => {
            fixture = TestBed.createComponent(ConfiguracaoColetorComponent);
            comp = fixture.componentInstance;
            service = fixture.debugElement.injector.get(ConfiguracaoColetorService);
        });

        describe('OnInit', () => {
            it('Should call load all on init', () => {
                // GIVEN
                const headers = new HttpHeaders().append('link', 'link;link');
                spyOn(service, 'query').and.returnValue(Observable.of(new HttpResponse({
                    body: [new ConfiguracaoColetor(123)],
                    headers
                })));

                // WHEN
                comp.ngOnInit();

                // THEN
                expect(service.query).toHaveBeenCalled();
                expect(comp.configuracaoColetors[0]).toEqual(jasmine.objectContaining({id: 123}));
            });
        });
    });

});
