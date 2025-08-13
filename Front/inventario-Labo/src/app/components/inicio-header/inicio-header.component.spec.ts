import { ComponentFixture, TestBed } from '@angular/core/testing';

import { InicioHeaderComponent } from './inicio-header.component';

describe('InicioHeaderComponent', () => {
  let component: InicioHeaderComponent;
  let fixture: ComponentFixture<InicioHeaderComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [InicioHeaderComponent]
    })
    .compileComponents();
    
    fixture = TestBed.createComponent(InicioHeaderComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
