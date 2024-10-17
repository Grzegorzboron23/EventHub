import React from 'react';
import './dropdown.css';

const Dropdown = ({ buttonLabel, items,className }) => {
  return (
    <div className={`dropdown customDropdown ${className ? className : ''}`}> 
      <button className="dropdown-toggle transform-13" type="button" data-bs-toggle="dropdown" aria-expanded="false">
        {buttonLabel}
      </button>
      <div className="dropdown-menu" aria-labelledby="dropdownMenuButton">
        {items.map((item, index) => (
          <a key={index} className="dropdown-item" href="#">
            {item}
          </a>
        ))}
      </div>
    </div>
  );
};



export default Dropdown;