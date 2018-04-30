/* tslint:disable max-line-length */
import { ComponentFixture, TestBed, async } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs/Observable';

import { JhipsterSampleApplicationTestModule } from '../../../test.module';
import { ColetorDetailComponent } from '../../../../../../main/webapp/app/entities/coletor/coletor-detail.component';
import { ColetorService } from '../../../../../../main/webapp/app/entities/coletor/coletor.service';
import { Coletor } from '../../../../../../main/webapp/app/entities/coletor/coletor.model';

describe('Component Tests', () => {

    describe('Coletor Management Detail Component', () => {
        let comp: ColetorDetailComponent;
        let fixture: ComponentFixture<ColetorDetailComponent>;
        let service: ColetorService;

        beforeEach(async(() => {
            TestBed.configureTestingModule({
                imports: [JhipsterSampleApplicationTestModule],
                declarations: [ColetorDetailComponent],
                providers: [
                    ColetorService
                ]
            })
            .overrideTemplate(ColetorDetailComponent, '')
            .compileComponents();
        }));

        beforeEach(() => {
            fixture = TestBed.createComponent(ColetorDetailComponent);
            comp = fixture.componentInstance;
            service = fixture.debugElement.injector.get(ColetorService);
        });

        describe('OnInit', () => {
            it('Should call load all on init', () => {
                // GIVEN

                spyOn(service, 'find').and.returnValue(Observable.of(new HttpResponse({
                    body: new Coletor(123)
                })));

                // WHEN
                comp.ngOnInit();

                // THEN
                expect(service.find).toHaveBeenCalledWith(123);
                expect(comp.coletor).toEqual(jasmine.objectContaining({id: 123}));
            });
        });
    });

});
