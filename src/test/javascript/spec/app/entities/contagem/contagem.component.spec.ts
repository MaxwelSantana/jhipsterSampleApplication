/* tslint:disable max-line-length */
import { ComponentFixture, TestBed, async } from '@angular/core/testing';
import { Observable } from 'rxjs/Observable';
import { HttpHeaders, HttpResponse } from '@angular/common/http';

import { JhipsterSampleApplicationTestModule } from '../../../test.module';
import { ContagemComponent } from '../../../../../../main/webapp/app/entities/contagem/contagem.component';
import { ContagemService } from '../../../../../../main/webapp/app/entities/contagem/contagem.service';
import { Contagem } from '../../../../../../main/webapp/app/entities/contagem/contagem.model';

describe('Component Tests', () => {

    describe('Contagem Management Component', () => {
        let comp: ContagemComponent;
        let fixture: ComponentFixture<ContagemComponent>;
        let service: ContagemService;

        beforeEach(async(() => {
            TestBed.configureTestingModule({
                imports: [JhipsterSampleApplicationTestModule],
                declarations: [ContagemComponent],
                providers: [
                    ContagemService
                ]
            })
            .overrideTemplate(ContagemComponent, '')
            .compileComponents();
        }));

        beforeEach(() => {
            fixture = TestBed.createComponent(ContagemComponent);
            comp = fixture.componentInstance;
            service = fixture.debugElement.injector.get(ContagemService);
        });

        describe('OnInit', () => {
            it('Should call load all on init', () => {
                // GIVEN
                const headers = new HttpHeaders().append('link', 'link;link');
                spyOn(service, 'query').and.returnValue(Observable.of(new HttpResponse({
                    body: [new Contagem(123)],
                    headers
                })));

                // WHEN
                comp.ngOnInit();

                // THEN
                expect(service.query).toHaveBeenCalled();
                expect(comp.contagems[0]).toEqual(jasmine.objectContaining({id: 123}));
            });
        });
    });

});
